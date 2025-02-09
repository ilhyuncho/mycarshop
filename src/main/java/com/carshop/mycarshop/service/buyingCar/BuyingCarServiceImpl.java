package com.carshop.mycarshop.service.buyingCar;

import com.carshop.mycarshop.common.exception.OwnerCarNotFoundException;
import com.carshop.mycarshop.domain.buyingCar.BuyCarStatus;
import com.carshop.mycarshop.domain.buyingCar.BuyingCar;
import com.carshop.mycarshop.domain.buyingCar.BuyingCarRepository;
import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarListResDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarRegDTO;
import com.carshop.mycarshop.dto.buyingCar.BuyingCarViewDTO;
import com.carshop.mycarshop.service.sellingCar.SellingCarService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
public class BuyingCarServiceImpl implements BuyingCarService {
    private final BuyingCarRepository buyingCarRepository;

    private final SellingCarService sellingCarService;

    public BuyingCarServiceImpl(BuyingCarRepository buyingCarRepository,
                                @Lazy SellingCarService sellingCarService){ // @Lazy : 순환 참조 해결을 위해
                                                                            // buyingCarServiceImpl, sellingCarServiceImpl
        this.buyingCarRepository = buyingCarRepository;
        this.sellingCarService = sellingCarService;
    }

    @Override
    public Optional<BuyingCar> getBuyingCarInfo(User user, SellingCar sellingCar){
        // 판매 차량을 사려고 하는 고객의 구매 정보 get
         return buyingCarRepository.findBySellingCarAndUserAndIsActive(sellingCar, user, true);
    }

    @Override
    public List<BuyingCarViewDTO> getListBuyingCarInfo(User user) { // 차량 구매 내역 조회

        return buyingCarRepository.findByUser(user)
                .stream()
                .filter(BuyingCar::getIsActive)     // Active 한 상태만 조회
                // null은 제외 (테스트용)
                //.filter(Objects::nonNull)
                .map(BuyingCarServiceImpl::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BuyingCarListResDTO<BuyingCarViewDTO> getPageBuyingCarInfo(PageRequestDTO pageRequestDTO, Long sellingCarId) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("proposalPrice");

        // 구매하려는 차량 정보
        SellingCar sellingCar = sellingCarService.getSellingCarInfo(sellingCarId);

        // 해당 차의 구매 요청 리스트 get
        Page<BuyingCarViewDTO> resultDTO = buyingCarRepository.getBuyingCarInfo(sellingCarId, pageable);
        List<BuyingCarViewDTO> listBuyingCarViewDTO = resultDTO.getContent();

        // 구매 희망 최고 가격 get
        int maxProposalPrice = 0;
        if(listBuyingCarViewDTO.size() > 0){
            // stream max, Comparator 활용
            maxProposalPrice = listBuyingCarViewDTO.stream()
                    .max(Comparator.comparingInt(BuyingCarViewDTO::getProposalPrice))
                    .get().getProposalPrice();
        }

        return BuyingCarListResDTO.<BuyingCarViewDTO>withSuper()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(listBuyingCarViewDTO)
                .total((int)resultDTO.getTotalElements())
                .highProposalPrice(maxProposalPrice)
                .requiredPrice(sellingCar.getRequiredPrice())
                .build();
    }

    @Override
    public void registerBuyingCar(User user, BuyingCarRegDTO buyingCarRegDTO) {

        BuyCarStatus buyCarStatus = BuyCarStatus.fromValue(buyingCarRegDTO.getOfferType());

        // 구매하려는 차량 정보
        SellingCar sellingCar = sellingCarService.getSellingCarInfo(buyingCarRegDTO.getSellingCarId());

        if(getBuyingCarInfo(user, sellingCar).isPresent()){
            throw new OwnerCarNotFoundException("이미 구매 신청 정보가 존재합니다");
        }

        BuyingCar buyingCar = BuyingCar.builder()
                .user(user)
                .sellingCar(sellingCar)
                .buyCarStatus(buyCarStatus)
                .isActive(true)
                .proposalPrice(buyingCarRegDTO.getRequestPrice())   // 경매 시 요청 가격
                .phoneNumber(buyingCarRegDTO.getPhoneNumber())      // 상담 시 전달한 전화번호
                .build();
        buyingCarRepository.save(buyingCar);
    }
    @Override
    public void updateBuyingCar(User user, BuyingCarRegDTO buyingCarRegDTO) {

        // 구매하려는 차량 정보
        SellingCar sellingCar = sellingCarService.getSellingCarInfo(buyingCarRegDTO.getSellingCarId());

        // 구매 신청 내역 get
        Optional<BuyingCar> result = getBuyingCarInfo(user, sellingCar);

        result.ifPresentOrElse(buyingCar -> {
            // 신청 상태 변경
            BuyCarStatus buyCarStatus = BuyCarStatus.fromValue(buyingCarRegDTO.getOfferType());
            buyingCar.changeBuyCarStatus(buyCarStatus);

            if(buyCarStatus == BuyCarStatus.PROPOSE_CHANGE_PRICE){
                buyingCar.changePrice(buyingCarRegDTO.getRequestPrice());
            }

        }, () -> {
            throw new OwnerCarNotFoundException("구매 신청 정보가 존재 하지 않습니다");
        });
    }

    private static BuyingCarViewDTO entityToDTO(BuyingCar buyingCar) {

        return BuyingCarViewDTO.builder( )
                .proposalPrice(buyingCar.getProposalPrice())
                .registerTime(buyingCar.getRegisterTime())
                .userName(buyingCar.getUser().getUserName())
                .buyCarStatus(buyingCar.getBuyCarStatus())
                .carNumber(buyingCar.getSellingCar().getCar().getCarNumber())
                .carDetailModel(buyingCar.getSellingCar().getCar().getRefCarInfo().getCarDetailModel())
                .carId(buyingCar.getSellingCar().getCar().getCarId())
                .sellingCarId(buyingCar.getSellingCar().getSellingCarId())
                .build();
    }

}