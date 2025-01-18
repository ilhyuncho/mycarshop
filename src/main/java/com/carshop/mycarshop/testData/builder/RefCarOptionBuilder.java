package com.carshop.mycarshop.testData.builder;

import com.carshop.mycarshop.domain.reference.RefCarOption;
import com.carshop.mycarshop.domain.reference.carType.CarDetailModel;

import java.util.*;

public class RefCarOptionBuilder {

    static RefCarOption refCarOption1 = RefCarOption.builder()
            .optionName("아방가르드").optionDesc("옵션1desc").build();

    static RefCarOption refCarOption2 = RefCarOption.builder()
            .optionName("익스클레시브").optionDesc("옵션1desc").build();

    static RefCarOption refCarOption3 = RefCarOption.builder()
            .optionName("옵션3").optionDesc("옵션3desc").build();

    static RefCarOption refCarOption4 = RefCarOption.builder()
            .optionName("옵션4").optionDesc("옵션4desc").build();


    public Map<CarDetailModel, Set<RefCarOption>> mapRefCarOption = new HashMap<>();

    public RefCarOptionBuilder(){
        // 임시로
        mapRefCarOption.put(CarDetailModel.CAR_DETAIL_MODEL_3SERIES_5G, Set.of(refCarOption1,refCarOption2));
        mapRefCarOption.put(CarDetailModel.CAR_DETAIL_MODEL_K5_DH, Set.of(refCarOption3));
        mapRefCarOption.put(CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_5G, Set.of(refCarOption4));

    }



}
