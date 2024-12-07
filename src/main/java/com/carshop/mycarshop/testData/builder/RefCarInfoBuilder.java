package com.carshop.mycarshop.testData.builder;

import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.reference.*;
import com.carshop.mycarshop.domain.reference.carType.CarCategory;
import com.carshop.mycarshop.domain.reference.carType.CarCompany;
import com.carshop.mycarshop.domain.reference.carType.CarDetailModel;
import com.carshop.mycarshop.domain.reference.carType.CarModel;

import java.util.ArrayList;
import java.util.List;

public class RefCarInfoBuilder {

    static RefCarInfo refCarInfo1 = RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SONATA.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SONATA_ADGE.getName())
            .carGrade(CarSize.MIDDLE).carOption("옵션3").company(CarCompany.CAR_COMPANY_HUNDAI.getName())
            .companyNation("대한민국").carYearStart(2023).carYearEnd(2024).build();
    static RefCarInfo refCarInfo2 = RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SONATA.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SONATA_NEW_RISE.getName())
            .carGrade(CarSize.MIDDLE).carOption("옵션3").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2019).carYearEnd(2023).build();
    static RefCarInfo refCarInfo3 = RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SONATA.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SONATA_LF.getName())
            .carGrade(CarSize.MIDDLE).carOption("옵션2").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2002).carYearEnd(2008).build();
    static RefCarInfo refCarInfo4= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_TUCSON.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_TUCSON_THE_NEW.getName())
            .carGrade(CarSize.MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2023).carYearEnd(2024).build();
    static RefCarInfo refCarInfo5= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_TUCSON.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_TUCSON_ALL_NEW.getName())
            .carGrade(CarSize.MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2015).carYearEnd(2020).build();
    static RefCarInfo refCarInfo6= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_TUCSON.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_TUCSON.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2004).carYearEnd(2009).build();
    static RefCarInfo refCarInfo7= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_AVANTE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_AVANTE_THE_NEW.getName())
            .carGrade(CarSize.LARGE).carOption("옵션1").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2023).carYearEnd(2024).build();
    static RefCarInfo refCarInfo8= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_AVANTE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_AVANTE_AD.getName())
            .carGrade(CarSize.MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2015).carYearEnd(2018).build();
    static RefCarInfo refCarInfo9= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_AVANTE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_AVANTE_THE_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2013).carYearEnd(2015).build();
///////////////////////////////////////////////////////////////////////////////////////////////
    ///////// 기아 //////////////
    static RefCarInfo refCarInfo10= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
        .carModel(CarModel.CAR_MODEL_K5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K5_DH.getName())
        .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
        .carYearStart(2013).carYearEnd(2015).build();
    static RefCarInfo refCarInfo11= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K5_THE_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2015).carYearEnd(2019).build();
    static RefCarInfo refCarInfo12= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2019).carYearEnd(2024).build();

    static RefCarInfo refCarInfo13= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K7.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K7_DH.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2013).carYearEnd(2017).build();
    static RefCarInfo refCarInfo14= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K7.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K7_THE_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2017).carYearEnd(2022).build();
    static RefCarInfo refCarInfo15= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K7.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K7_ALL_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2022).carYearEnd(2024).build();

    static RefCarInfo refCarInfo16= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K8.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K8_DH.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2011).carYearEnd(2015).build();
    static RefCarInfo refCarInfo17= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K8.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K8_THE_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2015).carYearEnd(2019).build();
    static RefCarInfo refCarInfo18= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K8.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K8_ALL_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2019).carYearEnd(2024).build();

    static RefCarInfo refCarInfo19= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SPORTAGE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_DH.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2010).carYearEnd(2014).build();
    static RefCarInfo refCarInfo20= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SPORTAGE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_THE_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2014).carYearEnd(2020).build();
    static RefCarInfo refCarInfo21= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SPORTAGE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_ALL_NEW.getName())
            .carGrade(CarSize.SMALL_MIDDLE).carOption("옵션1").company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2020).carYearEnd(2024).build();





    public List<RefCarInfo> listRefCarInfo = new ArrayList<>();

    public RefCarInfoBuilder(){
        listRefCarInfo.addAll(List.of(refCarInfo1,refCarInfo2,refCarInfo3,refCarInfo4,refCarInfo5,refCarInfo6,
                refCarInfo7,refCarInfo8,refCarInfo9,refCarInfo10,refCarInfo11,refCarInfo12,
                refCarInfo13,refCarInfo14,refCarInfo15,refCarInfo16,refCarInfo17,refCarInfo18,
                refCarInfo19,refCarInfo20,refCarInfo21
        ));
    }

}
