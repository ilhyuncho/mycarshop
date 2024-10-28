package com.carshop.mycarshop.domain.notification.search;

import com.carshop.mycarshop.domain.notification.NewsNotification;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class NewsNotificationSearchImpl implements NewsNotificationSearch {


    @Override
    public Page<NewsNotification> searchAll(String[] types, String keyword, Pageable pageable) {


        return null;
    }
}
