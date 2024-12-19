package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.domain.reference.RefCarSample;
import com.carshop.mycarshop.domain.reference.RefCarSampleRepository;
import com.carshop.mycarshop.dto.reference.RefCarSampleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class RefCarSampleServiceImpl implements RefCarSampleService {

    private final RefCarSampleRepository refCarSampleRepository;

    @Override
    public RefCarSampleDTO findMyCar(String carNumber) {

        if(carNumber.isEmpty()){
            // 임의로 차량 정보 db에서 get ( 테스트 간편하게... )
            List<RefCarSample> listCarSample = refCarSampleRepository.findAll();

            if(listCarSample.size() > 0){
                int skipIndex = new Random().nextInt(listCarSample.size() - 1);
                RefCarSample refCarSample = listCarSample.stream().skip(skipIndex).findFirst().get();

                return entityToDTO(refCarSample);
            }
            else{
                return null;
            }
        }
        else{
            Optional<RefCarSample> refCarSample = refCarSampleRepository.findByCarNumber(carNumber);
            return refCarSample.map(RefCarSampleServiceImpl::entityToDTO).orElse(null);
        }
    }

    private static RefCarSampleDTO entityToDTO(RefCarSample refCarSample) {
        if( refCarSample == null){
            return null;
        }
        return RefCarSampleDTO.builder()
                .refCarInfoId(refCarSample.getRefCarInfo().getRefCarInfoId())
                .carNumber(refCarSample.getCarNumber())
                .carColor(refCarSample.getCarColor())
                .carKm(refCarSample.getCarKm())
                .carYear(refCarSample.getCarYear())
                .carDetailModel(refCarSample.getRefCarInfo().getCarDetailModel())
                .company(refCarSample.getRefCarInfo().getCompany())
                .companyNation(refCarSample.getRefCarInfo().getCompanyNation())
                .regDate(refCarSample.getRegDate())
                .carGrade(refCarSample.getRefCarInfo().getCarGrade())
                .build();
    }
}
