package com.carshop.mycarshop.service.member;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS_COUNT = 3;
    private final LoadingCache<String, Integer> loginAttempCache;

    public LoginAttemptService() {
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

        log.error("LoginAttemptService-loginSuccess() memberId : " + memberId);

        loginAttempCache.invalidate(memberId);
    }

    public void loginFailed(String memberId) {

        log.error("LoginAttemptService-loginFailed() memberId : " + memberId);

        int failedAttemptCount = 0;

        try {
            failedAttemptCount = loginAttempCache.get(memberId);
        } catch (ExecutionException ignored) {
        }
        failedAttemptCount++;
        loginAttempCache.put(memberId, failedAttemptCount);
    }

    public boolean isBlocked(String memberId) {
        try {
            return loginAttempCache.get(memberId) >= MAX_ATTEMPTS_COUNT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
