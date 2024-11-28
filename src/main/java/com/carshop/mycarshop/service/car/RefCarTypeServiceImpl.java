package com.carshop.mycarshop.service.car;

import com.carshop.mycarshop.domain.car.RefCarType;
import com.carshop.mycarshop.domain.car.RefCarTypeRepository;
import com.carshop.mycarshop.dto.car.RefCarTypeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class RefCarTypeServiceImpl implements RefCarTypeService {

    private final RefCarTypeRepository refCarTypeRepository;

    public List<RefCarTypeDTO> getRefCarType(int parentTypeId){

        List<RefCarType> byGroupIndex = refCarTypeRepository.findByParentTypeId(parentTypeId);

        return byGroupIndex.stream().map(RefCarTypeServiceImpl::entityToDTO)
                             .collect(Collectors.toList());
    }

    private static RefCarTypeDTO entityToDTO(RefCarType refCarType) {
        RefCarTypeDTO refCarTypeDTO = RefCarTypeDTO.builder()
                .typeId(refCarType.getTypeId())
                .typeName(refCarType.getTypeName())
                .groupIndex(refCarType.getGroupIndex())
                .parentTypeId(refCarType.getParentTypeId())
                .order(refCarType.getOrder())
                .carYearStart(refCarType.getCarYearStart())
                .carYearEnd(refCarType.getCarYearEnd())
                .build();

        return refCarTypeDTO;
    }

}
