package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.domain.reference.RefCarInfo;
import com.carshop.mycarshop.dto.reference.RefCarTypeDTO;

import java.util.List;

public interface RefCarInfoService {
    RefCarInfo getRefCarInfo(Long parentTypeId);
}
