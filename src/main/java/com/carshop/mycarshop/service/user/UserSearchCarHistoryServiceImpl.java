package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.domain.sellingCar.SellingCarStatus;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserSearchCarHistory;
import com.carshop.mycarshop.domain.user.UserSearchCarHistoryRepository;
import com.carshop.mycarshop.dto.sellingCar.SellingCarResDTO;
import com.carshop.mycarshop.service.sellingCar.SellingCarServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserSearchCarHistoryServiceImpl implements UserSearchCarHistoryService{

    private final UserSearchCarHistoryRepository userSearchCarHistoryRepository;

    @Override
    public void insertSearchCarHistory(User user, SellingCar sellingCar) {

        // ifPresentOrElse -> 값이 비어 있을때, 있을때 모두 처리 가능
       userSearchCarHistoryRepository.findByUserAndSellingCar(user, sellingCar)
               .ifPresentOrElse(UserSearchCarHistory::plusSearchCount
                , () -> {
                    UserSearchCarHistory userSearchCarHistory = UserSearchCarHistory.builder()
                            .user(user)
                            .sellingCar(sellingCar)
                            .searchCount(1)
                            .build();

                    userSearchCarHistoryRepository.save(userSearchCarHistory);
                });
    }

    @Override
    public List<SellingCarResDTO> getSearchCarHistory(User user) {

        List<UserSearchCarHistory> listHistory = userSearchCarHistoryRepository.findByUser(user);

        return listHistory.stream()
                .sorted(Comparator.comparing(UserSearchCarHistory::getModDate))
                .map(UserSearchCarHistory::getSellingCar)   // SellingCar 객체로 변환
                .filter(sellingCar -> sellingCar.getSellingCarStatus() == SellingCarStatus.PROCESSING   // 판매 중 or 판매 완료
                        || sellingCar.getSellingCarStatus() == SellingCarStatus.COMPLETE)
                .map(SellingCarServiceImpl::entityToDTO)
                .map(sellingCarResDTO -> {     // 대표 이미지만 필터링 ( ImageOrder = 0 )
                    sellingCarResDTO.getFileNames().stream()
                            .filter(carImage -> !carImage.getIsMainImage())
                            .toList()
                            .forEach(x -> sellingCarResDTO.getFileNames().remove(x));
                    return sellingCarResDTO;
                })
                .limit(5)
                .peek(log::error)
                .collect(Collectors.toList());
    }


}