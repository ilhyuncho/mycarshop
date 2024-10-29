package com.carshop.mycarshop.domain.user.search;

import com.carshop.mycarshop.domain.user.PointSituation;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserPointHistory;
import com.carshop.mycarshop.dto.user.UserPointHistoryReqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPointHistorySearch {
    Page<UserPointHistory> searchUserPointHistory(String[] types, String keyword, Pageable pageable, UserPointHistoryReqDTO userPointHistoryReqDTO);
    List<UserPointHistory> getListPointHistoryBySituationType(User user, PointSituation pointSituation, String checkValue);


}