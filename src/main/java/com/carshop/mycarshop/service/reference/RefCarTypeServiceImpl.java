package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.domain.reference.carType.CarTypeGroup;
import com.carshop.mycarshop.dto.reference.RefCarTypeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class RefCarTypeServiceImpl implements RefCarTypeService {


    public List<RefCarTypeDTO> getRefCarType(int groupIndex, String parentMenuName){

        CarTypeGroup carTypeGroup = CarTypeGroup.fromValue(groupIndex);

        if(carTypeGroup != null){
            List<RefCarTypeDTO> listRefCarTypeDTO = CarTypeGroup.buildMetaDetailTypeDto(carTypeGroup.getTypeClass(), parentMenuName);

            listRefCarTypeDTO.forEach(log::error);
            return listRefCarTypeDTO;
        }

        return null;
    }

}
