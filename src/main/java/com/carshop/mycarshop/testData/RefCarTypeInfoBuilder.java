package com.carshop.mycarshop.testData;

import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.car.RefCarType;

import java.util.ArrayList;
import java.util.List;

public class RefCarTypeInfoBuilder {

    //    Map<String, List<String>> resultMap1 = new HashMap<>();
//        resultMap1.put("국산", List.of("현대", "기아", "kgm"));
//        resultMap1.put("수입", List.of("벤츠", "bmw", "테슬라"));
//
//    Map<String, List<String>> resultMap2 = new HashMap<>();
//        resultMap2.put("현대", List.of("쏘나타", "투싼", "아반떼"));
//        resultMap2.put("기아", List.of("k5", "스포티지", "k3"));
//        resultMap2.put("벤츠", List.of("c-class", "e-class", "s-class"));
//        resultMap2.put("bmw", List.of("5-series", "3-series", "x5"));
//
//    Map<String, List<String>> resultMap3 = new HashMap<>();
//        resultMap3.put("쏘나타", List.of("쏘나타 디 엣지(23년~현재)", "쏘나타 하이브리드(19년~23년)", "LF 쏘나타"));
//        resultMap3.put("투싼", List.of("더 뉴 투싼(23년~현재)", "올 뉴 투싼(15년~20년)", "투싼(04년~09년)"));
//        resultMap3.put("아반떼", List.of("더 뉴 아반떼(23년~현재)", "아반떼 AD(15년~18년)", "더 뉴 아반떼(13년~15년)"));
//

    static RefCarType refCarType1 = RefCarType.builder().typeId(1).groupIndex(1).typeName("국산").parentTypeId(0).order(1).build();
    static RefCarType refCarType2 = RefCarType.builder().typeId(2).groupIndex(1).typeName("수입").parentTypeId(0).order(2).build();

    static RefCarType refCarType3 = RefCarType.builder().typeId(3).groupIndex(2).typeName("현대").parentTypeId(1).order(1).build();
    static RefCarType refCarType4 = RefCarType.builder().typeId(4).groupIndex(2).typeName("기아").parentTypeId(1).order(2).build();
    static RefCarType refCarType5 = RefCarType.builder().typeId(5).groupIndex(2).typeName("kgm").parentTypeId(1).order(3).build();

    static RefCarType refCarType6 = RefCarType.builder().typeId(6).groupIndex(2).typeName("벤츠").parentTypeId(2).order(1).build();
    static RefCarType refCarType7 = RefCarType.builder().typeId(7).groupIndex(2).typeName("bmw").parentTypeId(2).order(2).build();
    static RefCarType refCarType8 = RefCarType.builder().typeId(8).groupIndex(2).typeName("테슬라").parentTypeId(2).order(3).build();

    static RefCarType refCarType9 = RefCarType.builder().typeId(9).groupIndex(3).typeName("쏘나타").parentTypeId(3).order(1).build();
    static RefCarType refCarType10 = RefCarType.builder().typeId(10).groupIndex(3).typeName("투싼").parentTypeId(3).order(2).build();
    static RefCarType refCarType11 = RefCarType.builder().typeId(11).groupIndex(3).typeName("아반떼").parentTypeId(3).order(3).build();
    static RefCarType refCarType12 = RefCarType.builder().typeId(12).groupIndex(3).typeName("k5").parentTypeId(4).order(1).build();
    static RefCarType refCarType13 = RefCarType.builder().typeId(13).groupIndex(3).typeName("스포티지").parentTypeId(4).order(2).build();
    static RefCarType refCarType14 = RefCarType.builder().typeId(14).groupIndex(3).typeName("쏘렌토").parentTypeId(4).order(3).build();
    static RefCarType refCarType15 = RefCarType.builder().typeId(15).groupIndex(3).typeName("c-class").parentTypeId(6).order(1).build();
    static RefCarType refCarType16 = RefCarType.builder().typeId(16).groupIndex(3).typeName("e-class").parentTypeId(6).order(2).build();
    static RefCarType refCarType17 = RefCarType.builder().typeId(17).groupIndex(3).typeName("s-class").parentTypeId(6).order(3).build();
    static RefCarType refCarType18 = RefCarType.builder().typeId(18).groupIndex(3).typeName("5-series").parentTypeId(7).order(1).build();
    static RefCarType refCarType19 = RefCarType.builder().typeId(19).groupIndex(3).typeName("7-series").parentTypeId(7).order(2).build();
    static RefCarType refCarType20 = RefCarType.builder().typeId(20).groupIndex(3).typeName("x5").parentTypeId(7).order(3).build();

    static RefCarType refCarType21 = RefCarType.builder().typeId(21).groupIndex(4).typeName("쏘나타 디 엣지").parentTypeId(9).order(1)
            .carYearStart(2023).carYearEnd(2024).build();
    static RefCarType refCarType22 = RefCarType.builder().typeId(22).groupIndex(4).typeName("쏘나타 하이브리드").parentTypeId(9).order(2)
            .carYearStart(2019).carYearEnd(2023).build();
    static RefCarType refCarType23 = RefCarType.builder().typeId(23).groupIndex(4).typeName("LF 쏘나타").parentTypeId(9).order(3)
            .carYearStart(2002).carYearEnd(2008).build();

    static RefCarType refCarType24 = RefCarType.builder().typeId(24).groupIndex(4).typeName("더 뉴 투싼").parentTypeId(10).order(1)
            .carYearStart(2023).carYearEnd(2024).build();
    static RefCarType refCarType25 = RefCarType.builder().typeId(25).groupIndex(4).typeName("올 뉴 투싼").parentTypeId(10).order(2)
            .carYearStart(2015).carYearEnd(2020).build();
    static RefCarType refCarType26 = RefCarType.builder().typeId(26).groupIndex(4).typeName("투싼").parentTypeId(10).order(3)
            .carYearStart(2004).carYearEnd(2009).build();
    static RefCarType refCarType27 = RefCarType.builder().typeId(27).groupIndex(4).typeName("더 뉴 아반떼").parentTypeId(11).order(1)
            .carYearStart(2023).carYearEnd(2024).build();
    static RefCarType refCarType28 = RefCarType.builder().typeId(28).groupIndex(4).typeName("아반떼 AD").parentTypeId(11).order(2)
            .carYearStart(2015).carYearEnd(2018).build();
    static RefCarType refCarType29 = RefCarType.builder().typeId(29).groupIndex(4).typeName("더 뉴 아반떼").parentTypeId(11).order(3)
            .carYearStart(2013).carYearEnd(2015).build();



    List<RefCarType> listRefCarType = new ArrayList<>();


    RefCarTypeInfoBuilder(){
        listRefCarType.addAll(List.of(refCarType1, refCarType2, refCarType3, refCarType4, refCarType5, refCarType6,
                refCarType7, refCarType8, refCarType9, refCarType10, refCarType11, refCarType12,
                refCarType13, refCarType14, refCarType15, refCarType16, refCarType17, refCarType18,
                refCarType19, refCarType20, refCarType21, refCarType22, refCarType23, refCarType24,
                refCarType15, refCarType26, refCarType27, refCarType28, refCarType29));
    }

}

