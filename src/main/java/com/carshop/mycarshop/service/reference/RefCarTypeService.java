package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.dto.reference.RefCarTypeDTO;

import java.util.List;

public interface RefCarTypeService {
    List<RefCarTypeDTO> getRefCarType(int groupIndex, String parentMenuName);
}
