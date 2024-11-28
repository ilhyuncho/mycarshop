package com.carshop.mycarshop.service.car;

import com.carshop.mycarshop.dto.car.RefCarTypeDTO;

import java.util.List;

public interface RefCarTypeService {
    List<RefCarTypeDTO> getRefCarType(int parentTypeId);
}
