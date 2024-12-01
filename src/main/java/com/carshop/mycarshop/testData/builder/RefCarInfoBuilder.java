package com.carshop.mycarshop.testData.builder;

import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.reference.RefCarInfo;

import java.util.ArrayList;
import java.util.List;

public class RefCarInfoBuilder {

    static RefCarInfo refCarInfo1 = RefCarInfo.builder().carModel("쏘나타 디 엣지").carGrade(CarSize.MIDDLE).carOption("옵션3")
            .company("현대").companyNation("대한민국").carYearStart(2023).carYearEnd(2024).build();
    static RefCarInfo refCarInfo2 = RefCarInfo.builder().carModel("쏘나타 하이브리드").carGrade(CarSize.MIDDLE).carOption("옵션3")
            .company("현대").companyNation("대한민국").carYearStart(2019).carYearEnd(2023).build();
    static RefCarInfo refCarInfo3 = RefCarInfo.builder().carModel("LF 쏘나타").carGrade(CarSize.MIDDLE).carOption("옵션2")
            .company("현대").companyNation("대한민국").carYearStart(2002).carYearEnd(2008).build();
    static RefCarInfo refCarInfo4= RefCarInfo.builder().carModel("더 뉴 투싼").carGrade(CarSize.MIDDLE).carOption("옵션1")
            .company("현대").companyNation("대한민국").carYearStart(2023).carYearEnd(2024).build();
    static RefCarInfo refCarInfo5= RefCarInfo.builder().carModel("올 뉴 투싼").carGrade(CarSize.MIDDLE).carOption("옵션1")
            .company("현대").companyNation("대한민국").carYearStart(2015).carYearEnd(2020).build();
    static RefCarInfo refCarInfo6= RefCarInfo.builder().carModel("투싼").carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1")
            .company("현대").companyNation("대한민국").carYearStart(2004).carYearEnd(2009).build();
    static RefCarInfo refCarInfo7= RefCarInfo.builder().carModel("더 뉴 아반떼").carGrade(CarSize.LARGE).carOption("옵션1")
            .company("현대").companyNation("대한민국").carYearStart(2023).carYearEnd(2024).build();
    static RefCarInfo refCarInfo8= RefCarInfo.builder().carModel("아반떼 AD").carGrade(CarSize.MIDDLE).carOption("옵션1")
            .company("현대").companyNation("대한민국").carYearStart(2015).carYearEnd(2018).build();
    static RefCarInfo refCarInfo9= RefCarInfo.builder().carModel("더 뉴 아반떼").carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1")
            .company("현대").companyNation("대한민국").carYearStart(2013).carYearEnd(2015).build();


    public List<RefCarInfo> listRefCarInfo = new ArrayList<>();

    public RefCarInfoBuilder(){
        listRefCarInfo.addAll(List.of(refCarInfo1,refCarInfo2,refCarInfo3,refCarInfo4,refCarInfo5,refCarInfo6,
                refCarInfo7,refCarInfo8,refCarInfo9));
    }

}
