package com.carshop.mycarshop.service.sellingCar;

import com.carshop.mycarshop.common.exception.OwnerCarNotFoundException;
import com.carshop.mycarshop.domain.buyingCar.BuyingCar;
import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.car.CarImage;
import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.domain.sellingCar.SellingCarRepository;
import com.carshop.mycarshop.domain.sellingCar.SellingCarStatus;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserActionType;
import com.carshop.mycarshop.domain.user.UserLike;
import com.carshop.mycarshop.domain.user.UserLikeRepository;
import com.carshop.mycarshop.dto.PageRequestExtDTO;
import com.carshop.mycarshop.dto.PageResponseDTO;
import com.carshop.mycarshop.dto.sellingCar.SellingCarRegDTO;
import com.carshop.mycarshop.dto.sellingCar.SellingCarResDTO;
import com.carshop.mycarshop.service.buyingCar.BuyingCarService;
import com.carshop.mycarshop.service.car.CarService;
import com.carshop.mycarshop.service.user.UserPointHistoryService;
import com.carshop.mycarshop.service.user.UserSearchCarHistoryService;
import com.carshop.mycarshop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SellingCarServiceImpl implements SellingCarService {

    private final SellingCarRepository sellingCarRepository;
    private final UserLikeRepository userLikeRepository;

    private final UserSearchCarHistoryService userSearchCarHistoryService;
    private final UserPointHistoryService userPointHistoryService;
    private final BuyingCarService buyingCarService;
    private final CarService carService;
    private final UserService userService;

    @Override
    public SellingCar getSellingCarInfo(Long sellingCarId) {
        return sellingCarRepository.findById(sellingCarId)
                .orElseThrow(() -> new NoSuchElementException("해당 차량 정보가 존재하지않습니다"));
    }

    @Override
    public SellingCarResDTO getSellingCarData(User user, Long sellingCarId) {

        // 판매 차량 정보 get
        SellingCar sellingCar = getSellingCarInfo(sellingCarId);

        SellingCarResDTO sellingCarResDTO = entityToDTO(sellingCar);

        // 로그인 한 고객일 경우
        if(user != null){
            boolean isOwner = Objects.equals(sellingCar.getUser().getUserId(), user.getUserId());

            // 해당 고객이 구매 요청을 했었는지 확인
            if(!isOwner){
               buyingCarService.getBuyingCarInfo(user, sellingCar)
                       .ifPresent( buyingCar -> {
                               sellingCarResDTO.setBuyCarStatus(buyingCar.getBuyCarStatus());
                               sellingCarResDTO.setProposalPrice(buyingCar.getProposalPrice());
                           });
            }
            // 좋아요 상태 update
            userLikeRepository.findByUserAndSellingCar(user, sellingCar)
                    .ifPresent(userLike -> sellingCarResDTO.setIsLike(userLike.getIsLike()));

            // 검색 기록 저장
            userSearchCarHistoryService.insertSearchCarHistory(user, sellingCar);
        }

        // 소유자 외의 고객이 검색 했을때
        if(user == null || !Objects.equals(user.getUserId(), sellingCar.getUser().getUserId()) ){
            sellingCar.changeViewCount();
        }

        return sellingCarResDTO;
    }


    @Override
    public PageResponseDTO<SellingCarResDTO> getListSellingCar(PageRequestExtDTO pageRequestExtDT) {

        Pageable pageable = pageRequestExtDT.getPageable("regDate");

        // 검색 기능 추가 버전 ( querydsl )
        Page<SellingCar> sellingCars = sellingCarRepository.searchAll(pageable, pageRequestExtDT);

        List<SellingCarResDTO> listSellingCarResDTO = sellingCars.getContent().stream()
                .map(SellingCarServiceImpl::entityToDTO)
                .collect(Collectors.toList());

        return PageResponseDTO.<SellingCarResDTO>withAll()
                .pageRequestDTO(pageRequestExtDT)
                .dtoList(listSellingCarResDTO)
                .total((int)sellingCars.getTotalElements())
                .build();
    }

    @Override
    public List<SellingCarResDTO> getListRecommend(String memberName){

        Long userId = 0L;
        if(memberName != null && !memberName.isEmpty()){
            User user = userService.findUser(memberName);
            userId = user.getUserId();
        }

        List<SellingCar> recommendSellingCar = sellingCarRepository.findRecommendSellingCar(4, userId);

        return recommendSellingCar.stream()
                .map(SellingCarServiceImpl::entityToDTO)
                .map(SellingCarResDTO -> {     // 대표 이미지만 필터링 ( ImageOrder = 0 )
                    SellingCarResDTO.getFileNames().stream()
                            .filter(carImage -> !carImage.getIsMainImage())
                            .toList()
                            .forEach(x -> SellingCarResDTO.getFileNames().remove(x));
                    return SellingCarResDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void registerSellingCar(User user, SellingCarRegDTO sellingCarRegDTO) {

        Car car = carService.getCarInfo(sellingCarRegDTO.getCarId());

        if(car.getImageSet().isEmpty()){
            throw new OwnerCarNotFoundException("차량 판매시 최소 한장의 대표 사진을 등록해야 합니다!!");
        }

        car.registerSellingCar(sellingCarRegDTO);

        // 포인트 획득 처리
        userPointHistoryService.gainUserPoint(user, UserActionType.ACTION_REG_SELLING_CAR, car.getCarNumber());
    }
    @Override
    public void likeSellingCar(User user, SellingCarRegDTO sellingCarRegDTO) {

        Car car = carService.getCarInfo(sellingCarRegDTO.getCarId());

        if(car.getSellingCar() != null){
           userLikeRepository.findByUserAndSellingCar(user, car.getSellingCar())
                   .ifPresentOrElse(
                           userLike -> { userLike.changeLike(sellingCarRegDTO.getIsLike()); },
                           () -> {
                               userLikeRepository.save(UserLike.builder()
                                       .user(user)
                                       .sellingCar(car.getSellingCar())
                                       .isLike(sellingCarRegDTO.getIsLike())
                                       .build());
                   });

            car.getSellingCar().changeLikeCount(sellingCarRegDTO.getIsLike());
        }
    }

    @Override
    public void updateSellingCar(SellingCarRegDTO sellingCarRegDTO) {

        Car car = carService.getCarInfo(sellingCarRegDTO.getCarId());
        SellingCar sellingCar = car.getSellingCar();

        if(sellingCar == null || SellingCarStatus.PROCESSING != sellingCar.getSellingCarStatus()) {
            throw new OwnerCarNotFoundException("소유 차가 판매 중이 아닙니다");
        }

        car.updateCellingCarStatus(sellingCarRegDTO.getSellingCarStatus());
    }
    public static SellingCarResDTO entityToDTO(SellingCar sellingCar) {

        SellingCarResDTO sellingCarResDTO = SellingCarResDTO.builder()
                .carId(sellingCar.getCar().getCarId())
                .requiredPrice(sellingCar.getRequiredPrice())
                .sellingCarStatus(sellingCar.getSellingCarStatus())
                .expiredDate(sellingCar.getExpiredDate())
                .carNumber(sellingCar.getCar().getCarNumber())
                .carCompany(sellingCar.getCar().getRefCarInfo().getCompany())
                .carDetailModel(sellingCar.getCar().getRefCarInfo().getCarDetailModel())
                .carYears(sellingCar.getCar().getCarYears())
                .carKm(sellingCar.getCar().getCarKm())

                .carGradeName(sellingCar.getCar().getRefCarGrade().getName())
                .carGradeDesc(sellingCar.getCar().getRefCarGrade().getNameDesc())
                .carTrimName(sellingCar.getCar().getRefCarTrim().getName())
                .carTrimDesc(sellingCar.getCar().getRefCarTrim().getNameDesc())
                .carFuelType(sellingCar.getCar().getCarFuelType())

                .sellingCarId(sellingCar.getSellingCarId())
                .viewCount(sellingCar.getViewCount())
                .sellType(sellingCar.getSellType())
                .isLike(false)
                .build();

        sellingCar.getCar().getImageSet()
                .stream().sorted(Comparator.comparing(CarImage::getImageOrder))
                .forEach(image -> {
                    sellingCarResDTO.addImage(image.getCarImageId(), image.getUuid(), image.getFileName(),
                            image.getImageOrder(), image.getIsMainImage());
                });

        return sellingCarResDTO;
    }

}
