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

        if(carNumber == null || carNumber.isBlank()){
            return getRandomCarSample();
        }

        return refCarSampleRepository.findByCarNumber(carNumber)
                    .map(RefCarSampleServiceImpl::entityToDTO)
                    .orElse(null);
    }

    private RefCarSampleDTO getRandomCarSample() {

        List<RefCarSample> listCarSample = refCarSampleRepository.findAll();

        if(listCarSample.size() > 0){
            RefCarSample refCarSample = listCarSample.get(new Random().nextInt(listCarSample.size()));
            return entityToDTO(refCarSample);
        }
        else{
            log.error("RefCarSample Data is Invalid!!!!!!!!!!!!");
            return null;
        }
    }

    public static RefCarSampleDTO entityToDTO(RefCarSample refCarSample) {

        if( refCarSample == null){
            return null;
        }

        return RefCarSampleDTO.builder()
                .refCarInfoId(refCarSample.getRefCarInfo().getRefCarInfoId())
                .carNumber(refCarSample.getCarNumber())
                .carColor(refCarSample.getCarColor())
                .carKm(refCarSample.getCarKm())

                .refCarGrade(refCarSample.getRefCarGrade())
                .refCarTrim(refCarSample.getRefCarTrim())
                .carFuelType(refCarSample.getCarFuelType())

                .carYear(refCarSample.getCarYear())
                .carDetailModel(refCarSample.getRefCarInfo().getCarDetailModel())
                .company(refCarSample.getRefCarInfo().getCompany())
                .companyNation(refCarSample.getRefCarInfo().getCompanyNation())
                .regDate(refCarSample.getRegDate())
                .carSize(refCarSample.getRefCarInfo().getCarSize())
                .build();
    }
}
