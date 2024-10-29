package com.carshop.mycarshop.domain.user;

import com.carshop.mycarshop.domain.user.search.UserPointHistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPointHistoryRepository extends JpaRepository<UserPointHistory, Long>, UserPointHistorySearch {

    List<UserPointHistory> findByUser(User user);

    @Query("SELECT COUNT(*) FROM UserPointHistory a WHERE a.user = :user")
    int getCountUserPointHistory(@Param("user") User user);


}