package com.carshop.mycarshop.service.member;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS_COUNT = 3;
    private LoadingCache<String, Integer> loginAttempCache;

    public LoginAttemptService(){
        loginAttempCache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }

    public void loginSuccess(String memberId) {
        loginAttempCache.invalidate(memberId);
    }

    public void loginFailed(String memberId)  {

        log.error("LoginAttemptService-loginFailed() memberId : " + memberId);

        int failedAttemptCount = 0;

        try {
            failedAttemptCount = loginAttempCache.get(memberId);
        }
        catch (ExecutionException ignored){
        }
        failedAttemptCount++;
        loginAttempCache.put(memberId, failedAttemptCount);
    }

    public boolean isBlocked(String memberId) {
        try{
            return loginAttempCache.get(memberId) >= MAX_ATTEMPTS_COUNT;
        }
        catch(ExecutionException e){
            return false;
        }
    }




}
