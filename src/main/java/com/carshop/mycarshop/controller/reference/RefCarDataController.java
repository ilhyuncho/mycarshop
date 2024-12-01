package com.carshop.mycarshop.controller.reference;

import com.carshop.mycarshop.dto.reference.RefCarTypeDTO;
import com.carshop.mycarshop.service.reference.RefCarTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
@Log4j2
public class RefCarDataController {

    private final RefCarTypeService refCarTypeService;

    @ApiOperation(value = "판매 차량 종류 리스트 전달", notes = "referenceData 전달")
    @GetMapping("/listRefCarType")
    public List<RefCarTypeDTO> getListRefCarType(int groupNumber, int parentTypeId){

        log.error("listRefCarType()~~~~~~~" + groupNumber  + ", " + parentTypeId );

        return refCarTypeService.getRefCarType(parentTypeId);
    }

}
