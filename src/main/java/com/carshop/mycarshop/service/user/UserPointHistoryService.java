package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.reference.RefPointSituation;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserActionType;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.user.UserListPointHistoryResDTO;
import com.carshop.mycarshop.dto.user.UserPointHistoryReqDTO;
import com.carshop.mycarshop.dto.user.UserPointHistoryResDTO;

public interface UserPointHistoryService {

    void gainUserPoint(String memberId, UserActionType userActionType, String... varCheckValue);
    void consumeUserPoint(String memberId, UserActionType userActionType, int consumePoint);
    void cancelUserPoint(String memberId, UserActionType userActionType, int consumePoint);

    RefPointSituation checkMissionIncomplete(User user, UserActionType userActionType, String checkValue);

    UserListPointHistoryResDTO<UserPointHistoryResDTO>
    getListUserPointHistory(PageRequestDTO pageRequestDTO, User user, UserPointHistoryReqDTO userPointHistoryReqDTO);
}