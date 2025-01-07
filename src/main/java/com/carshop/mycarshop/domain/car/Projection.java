package com.carshop.mycarshop.domain.car;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

public class Projection {       // 인터페이스 기반 Projection

    public interface CarSummary{
        Long getCarId();                // 1.Closed Projection ( 필요한 컬럼들만 가져온다 )
        String getCarNumber();

        // @Value("#{target.carNumber} #{target.carModel}")    // 인터페이스 기반 2.Open Projection ( 모든 컬럼을 가져옴 )
        // String getCarInfo();                                // 초기 환경 객체를 호출하는 방법
        
        default String getCarInfo(){                // 위 코드 Projection의 성능 개선
            return getCarNumber() + "_(carid: " + getCarId() + ")";      // 필요한 컬럼만 가져오게 된다
        }
    }

    public static class CarSummary2{
        private Long carId;
        private String carNumber;

        public CarSummary2(String carNumber,Long carId){
            this.carId = carId;
            this.carNumber = carNumber;
        }

        public String getCarNumber(){
            return carNumber;
        }
        public Long getCarId(){
            return carId;
        }
    }


}