package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.reference.RefPointSituation;
import com.carshop.mycarshop.domain.reference.RefPointSituationRepository;
import com.carshop.mycarshop.domain.user.*;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.user.UserListPointHistoryResDTO;
import com.carshop.mycarshop.dto.user.UserPointHistoryReqDTO;
import com.carshop.mycarshop.dto.user.UserPointHistoryResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserPointHistoryServiceImpl implements UserPointHistoryService {

    private final UserPointHistoryRepository userPointHistoryRepository;
    private final RefPointSituationRepository refPointSituationRepository;


    @Override
    public void gainUserPoint(User user, UserActionType userActionType, String...varCheckValue) {

        String checkValue = Arrays.stream(varCheckValue)
                .findFirst()
                .orElse(null);  // 예) 차량 판매 시 판매 하는 차량 번호

        Optional.ofNullable(checkMissionIncomplete(user, userActionType, checkValue))
                .ifPresent(refPointSituation -> {
                    // 유저 포인트 획득 처리
                    user.addMPoint(refPointSituation.getGainPoint());
                    // 히스토리 추가
                    saveUserPointHistory(user, refPointSituation, checkValue);
                });
    }

    private void saveUserPointHistory(User user, RefPointSituation refPointSituation, String checkValue) {

        userPointHistoryRepository.save(UserPointHistory.builder()
                .user(user)
                .pointType(PointType.GAIN)
                .pointSituation(PointSituation.fromValue(refPointSituation.getRefPointSituationId()))
                .pointValue(refPointSituation.getGainPoint())
                .checkValue(checkValue)
                .build());
    }

    @Override
    public void consumeUserPoint(User user, UserActionType userActionType, int consumePoint){

        userPointHistoryRepository.save(UserPointHistory.builder()
                .user(user)
                .pointType(PointType.CONSUME)
                .pointSituation(PointSituation.BUY_ITEM_WITH_POINT)
                .pointValue(consumePoint * -1)
                .build());
    }

    @Override
    public void cancelUserPoint(User user, UserActionType userActionType, int returnPoint) {

        userPointHistoryRepository.save(UserPointHistory.builder()
                .user(user)
                .pointType(PointType.RETURN)
                .pointSituation(PointSituation.CANCEL_ITEM_RETURN_POINT)
                .pointValue(returnPoint)
                .build());
    }

    @Override
    public RefPointSituation checkMissionIncomplete(User user, UserActionType userActionType, String checkValue) {

        log.error("checkMissionIncomplete()!" + userActionType + "," + checkValue);
        // 총 성공 미션 갯수 get
        int countUserPointHistory = userPointHistoryRepository.getCountUserPointHistory(user);

        // First login case
        if(countUserPointHistory == 0 && userActionType.equals(UserActionType.ACTION_LOGIN)){
            return findPointSituation(PointSituation.FIRST_LOGIN);
        }

        PointSituation pointSituation = convertPointSituation(userActionType);

        List<UserPointHistory> listUserPointHistory =
                userPointHistoryRepository.getListPointHistoryBySituationType(user, pointSituation, checkValue);

        return listUserPointHistory.isEmpty() ? findPointSituation(pointSituation) : null;
    }

    private RefPointSituation findPointSituation(PointSituation pointSituation) {
        return refPointSituationRepository.findById(pointSituation.getType())
                .orElseThrow(() -> new NoSuchElementException("해당 미션 정보가 존재하지 않습니다"));
    }

    @Override
    public UserListPointHistoryResDTO<UserPointHistoryResDTO> getListUserPointHistory(PageRequestDTO pageRequestDTO,
                                                                                      User user, UserPointHistoryReqDTO userPointHistoryReqDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("regDate");

        Page<UserPointHistory> result = userPointHistoryRepository.searchUserPointHistory(types, keyword, pageable, user, userPointHistoryReqDTO);

        // Page는 map을 지원해서 내부 데이터를 다른것으로 변경 가능
        List<UserPointHistoryResDTO> dtoList = result.map(UserPointHistoryServiceImpl::entityToDTO)
                .stream().collect(Collectors.toList());

        dtoList.forEach(log::error);
        // List.copyOf 활용 예 ( 불변 객체 리턴 )
        //List<UserMissionResDTO> unModifyCartDTOList = List.copyOf(dtoList);

        // 에러 발생!!!
        //unModifyCartDTOList.add(CartResponseDTO.builder().build());

        // 하지만 list안이 객체라면 수정 가능 하다..( 주의 해서 사용해야 함 )
        //unModifyCartDTOList.get(0).setItemName("update Temp");

//        return new UserListPointHistoryResDTO<UserPointHistoryResDTO>(
//                pageRequestDTO, dtoList,
//                (int)result.getTotalElements(), user.getMPoint());
        return UserListPointHistoryResDTO.<UserPointHistoryResDTO>withSuper()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .totalMPoint(user.getMPoint())
                .build();
    }

    public static PointSituation convertPointSituation(UserActionType userActionType){
        return switch (userActionType) {
            case ACTION_LOGIN -> PointSituation.DAILY_LOGIN;
            case ACTION_REG_MY_CAR -> PointSituation.REGISTER_CAR;
            case ACTION_REG_SELLING_CAR -> PointSituation.SELL_CAR;
            default -> PointSituation.SITUATION_NONE;
        };
    }

    private static UserPointHistoryResDTO entityToDTO(UserPointHistory userPointHistory) {

        return UserPointHistoryResDTO.builder()
                .situationName(userPointHistory.getPointSituation().getTypeName())
                .pointTypeName(userPointHistory.getPointType().getName())
                .pointValue(userPointHistory.getPointType() == PointType.CONSUME ?
                        userPointHistory.getPointValue() * -1 : userPointHistory.getPointValue())
                .regDate(userPointHistory.getRegDate().toLocalDate())
                .build();
    }
}