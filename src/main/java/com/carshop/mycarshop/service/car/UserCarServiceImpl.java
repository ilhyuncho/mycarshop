package com.carshop.mycarshop.service.car;

import com.carshop.mycarshop.common.exception.AlreadyRegisterException;
import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.car.CarRepository;
import com.carshop.mycarshop.domain.car.Projection;
import com.carshop.mycarshop.domain.reference.RefCarInfo;
import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserActionType;
import com.carshop.mycarshop.dto.car.CarInfoReqDTO;
import com.carshop.mycarshop.dto.car.CarKmUpdateReqDTO;
import com.carshop.mycarshop.dto.car.CarViewResDTO;
import com.carshop.mycarshop.dto.reference.RefCarSampleDTO;
import com.carshop.mycarshop.service.reference.RefCarInfoService;
import com.carshop.mycarshop.service.reference.RefCarSampleService;
import com.carshop.mycarshop.service.user.UserPointHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserCarServiceImpl implements UserCarService {

    private final CarRepository carRepository;

    private final CarService carService;
    private final UserPointHistoryService userPointHistoryService;
    private final RefCarSampleService refCarSampleService;
    private final RefCarInfoService refCarInfoService;


    @Override
    public List<CarViewResDTO> readMyCarList(User user){
        // 보유 Car list
        List<Car> listOwnCar = user.getOwnCars();

        return listOwnCar.stream()
                .filter(Car::getIsActive)       // 판매 차량 제외
                .map(UserCarServiceImpl::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarViewResDTO readMyCarDetailInfo(User user, Long carId) {
        // 보유 Car list
        List<CarViewResDTO> listCarViewDTO = readMyCarList(user);

        // 요청된 carId 정보만 필터
        CarViewResDTO carViewResDTO = listCarViewDTO.stream()
                .filter(car -> Objects.equals(car.getCarId(), carId))
                .findFirst()
                .orElse(null);

        return carViewResDTO;
    }

    @Override
    public Long registerMyCar(User user, String carNumber) {

        // 유저의 기존 등록 차 정보 get ( Projection용 메서드는 하나만 정의 후 전달 받은 프로젝션 타입으로 사용 )
        List<Projection.CarSummary> userCarList = carRepository.findByUser(user, Projection.CarSummary.class);
        //List<Projection.CarSummary2> userCarList = carRepository.findByUser(user, Projection.CarSummary2.class);

        userCarList.forEach(a->{
            log.error(a.getCarNumber());
            log.error(a.getCarId());
        });

        boolean isRegister = userCarList.stream()
                .anyMatch(carSummary -> carSummary.getCarNumber().equals(carNumber));

        if(isRegister){
            throw new AlreadyRegisterException("이미 등록된 차량입니다.");
        }

        // 등록 하려는 차 정보 get
        RefCarSampleDTO refCarSampleDTO = refCarSampleService.findMyCar(carNumber);

        // refCarInfo 데이터 get
        RefCarInfo refCarInfo = refCarInfoService.getRefCarInfo(refCarSampleDTO.getRefCarInfoId());

        // 차 등록
        Car car = Car.builder(user, refCarSampleDTO, refCarInfo)
                     .build();

        // 포인트 획득 처리
        userPointHistoryService.gainUserPoint(user, UserActionType.ACTION_REG_MY_CAR, car.getCarNumber());

        return carRepository.save(car).getCarId();
    }
    @Override
    public void modifyMyCar(CarInfoReqDTO carInfoReqDTO) {

        Car car = carService.getCarInfo(carInfoReqDTO.getCarId());

        // 차량 스펙 업데이트
        car.changeSpec(carInfoReqDTO.getCarKm(), carInfoReqDTO.getCarYears(), carInfoReqDTO.getCarColors());

        // 차량 이미지 파일 재설정
        car.resetImages(carInfoReqDTO.getFileNames(), carInfoReqDTO.getMainImageFileName());

        carRepository.save(car);
    }

    @Override
    public void modifyMyCarKm(CarKmUpdateReqDTO carKmUpdateReqDTO) {

        Car car = carService.getCarInfo(carKmUpdateReqDTO.getCarId());

        car.changeKm(carKmUpdateReqDTO.getUpdateKmValue());
    }

    @Override
    public void deleteMyCar(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        car.ifPresent(carRepository::delete);
    }

    private static CarViewResDTO entityToDTO(Car car) {
        CarViewResDTO carViewResDTO = CarViewResDTO.writeCarViewDTOBuilder()
                .carId(car.getCarId())
                .userName(car.getUser().getUserName())
                .memberId(car.getUser().getMember().getMemberId())
                .carNumber(car.getCarNumber())
                .carColors(car.getCarColors())
                .carKm(car.getCarKm())
                .carGrade(car.getRefCarInfo().getCarGrade().getValue())
                .carDetailModel(car.getRefCarInfo().getCarDetailModel())
                .carYears(car.getCarYears())
                .build();

        // 판매 진행 정보 매핑
        if(!Objects.isNull(car.getSellingCar())){
            carViewResDTO.setSellingCarId(car.getSellingCar().getSellingCarId());
            carViewResDTO.setSellingCarStatus(car.getSellingCar().getSellingCarStatus());
            carViewResDTO.setSellType(car.getSellingCar().getSellType());
        }

        // 차 이미지 파일 정보 매핑
        car.getImageSet().forEach(carImage -> {
            carViewResDTO.addImage(carImage.getCarImageId(), carImage.getUuid(), carImage.getFileName(),
                    carImage.getImageOrder(), carImage.getIsMainImage());
        });

        return carViewResDTO;
    }

}