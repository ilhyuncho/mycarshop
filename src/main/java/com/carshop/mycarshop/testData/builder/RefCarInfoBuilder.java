package com.carshop.mycarshop.testData.builder;

import com.carshop.mycarshop.domain.car.CarSize;
import com.carshop.mycarshop.domain.reference.*;
import com.carshop.mycarshop.domain.reference.carType.*;

import java.util.ArrayList;
import java.util.List;

public class RefCarInfoBuilder {

    static RefCarInfo refCarInfo1 = RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SONATA.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SONATA_EDGE.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName())
            .companyNation("대한민국").carYearStart(2023).carYearEnd(2025)
            .build();
    static RefCarInfo refCarInfo2 = RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SONATA.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SONATA_NEW_RISE.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2019).carYearEnd(2023).build();
    static RefCarInfo refCarInfo3 = RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SONATA.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SONATA_LF.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2002).carYearEnd(2008).build();
    static RefCarInfo refCarInfo4= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_TUCSON.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_TUCSON_THE_NEW.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2023).carYearEnd(2025).build();
    static RefCarInfo refCarInfo5= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_TUCSON.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_TUCSON_ALL_NEW.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2015).carYearEnd(2020).build();
    static RefCarInfo refCarInfo6= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_TUCSON.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_TUCSON.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2004).carYearEnd(2009).build();
    static RefCarInfo refCarInfo7= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_AVANTE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_AVANTE_THE_NEW.getName())
            .carSize(CarSize.LARGE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2023).carYearEnd(2025).build();
    static RefCarInfo refCarInfo8= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_AVANTE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_AVANTE_AD.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2015).carYearEnd(2018).build();
    static RefCarInfo refCarInfo9= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_AVANTE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_AVANTE_ALL_NEW.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_HUNDAI.getName()).companyNation("대한민국")
            .carYearStart(2013).carYearEnd(2015).build();
///////////////////////////////////////////////////////////////////////////////////////////////
    ///////// 기아 //////////////
    static RefCarInfo refCarInfo10= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K5_DH.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2013).carYearEnd(2015).build();
    static RefCarInfo refCarInfo11= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K5_NEW.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2015).carYearEnd(2019).build();
    static RefCarInfo refCarInfo12= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2019).carYearEnd(2025).build();

    static RefCarInfo refCarInfo13= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K7.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K7_AH.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2013).carYearEnd(2017).build();
    static RefCarInfo refCarInfo14= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K7.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K7_NEW.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2017).carYearEnd(2022).build();
    static RefCarInfo refCarInfo15= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K7.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K7_ALL_NEW.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2022).carYearEnd(2025).build();

    static RefCarInfo refCarInfo16= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_K8.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_K8_SD.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2022).carYearEnd(2025).build();
    static RefCarInfo refCarInfo17= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SPORTAGE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_DH.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2010).carYearEnd(2014).build();
    static RefCarInfo refCarInfo18= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SPORTAGE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_THE_NEW.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2014).carYearEnd(2020).build();
    static RefCarInfo refCarInfo19= RefCarInfo.builder().carCategory(CarCategory.CAR_DOMESTIC.getName())
            .carModel(CarModel.CAR_MODEL_SPORTAGE.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_ALL_NEW.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_KIA.getName()).companyNation("대한민국")
            .carYearStart(2020).carYearEnd(2025).build();

    // 벤츠
    static RefCarInfo refCarInfo20= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_A_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_A_CLASS_5G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2010).carYearEnd(2015).build();
    static RefCarInfo refCarInfo21= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_A_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_A_CLASS_6G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2015).carYearEnd(2021).build();
    static RefCarInfo refCarInfo22= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_A_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_A_CLASS_7G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2021).carYearEnd(2025).build();

    static RefCarInfo refCarInfo23= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_C_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_4G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2008).carYearEnd(2013).build();
    static RefCarInfo refCarInfo24= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_C_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_5G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2013).carYearEnd(2019).build();
    static RefCarInfo refCarInfo25= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_C_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_6G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2019).carYearEnd(2025).build();

    static RefCarInfo refCarInfo26= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_E_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_5G.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2012).carYearEnd(2017).build();
    static RefCarInfo refCarInfo27= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_E_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_6G.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2017).carYearEnd(2024).build();
    static RefCarInfo refCarInfo28= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_E_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_7G.getName())
            .carSize(CarSize.MIDDLE).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2024).carYearEnd(2025).build();

    static RefCarInfo refCarInfo29= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_S_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_S_CLASS_7G.getName())
            .carSize(CarSize.LARGE).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2013).carYearEnd(2017).build();
    static RefCarInfo refCarInfo30= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_S_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_S_CLASS_8G.getName())
            .carSize(CarSize.LARGE).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2017).carYearEnd(2023).build();
    static RefCarInfo refCarInfo31= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_S_CLASS.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_S_CLASS_9G.getName())
            .carSize(CarSize.LARGE).company(CarCompany.CAR_COMPANY_BENZ.getName()).companyNation("독일")
            .carYearStart(2023).carYearEnd(2025).build();

    // BMW
    static RefCarInfo refCarInfo32= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_3_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_3SERIES_5G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2007).carYearEnd(2013).build();
    static RefCarInfo refCarInfo33= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_3_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_3SERIES_6G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2013).carYearEnd(2020).build();
    static RefCarInfo refCarInfo34= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_3_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_3SERIES_7G.getName())
            .carSize(CarSize.SMALL).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2020).carYearEnd(2025).build();

    static RefCarInfo refCarInfo35= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_5_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_5SERIES_6G.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2013).carYearEnd(2018).build();
    static RefCarInfo refCarInfo36= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_5_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_5SERIES_7G.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2018).carYearEnd(2024).build();
    static RefCarInfo refCarInfo37= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_5_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_5SERIES_8G.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2024).carYearEnd(2025).build();

    static RefCarInfo refCarInfo38= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_7_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_7SERIES_4G.getName())
            .carSize(CarSize.LARGE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2012).carYearEnd(2017).build();
    static RefCarInfo refCarInfo39= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_7_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_7SERIES_5G.getName())
            .carSize(CarSize.LARGE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2017).carYearEnd(2024).build();
    static RefCarInfo refCarInfo40= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_7_SERIES.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_7SERIES_6G.getName())
            .carSize(CarSize.LARGE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2024).carYearEnd(2025).build();

    static RefCarInfo refCarInfo41= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_X5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_X5_1G.getName())
            .carSize(CarSize.MIDDLE_LARGE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2017).carYearEnd(2023).build();
    static RefCarInfo refCarInfo42= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_X5.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_X5_2G.getName())
            .carSize(CarSize.MIDDLE_LARGE).company(CarCompany.CAR_COMPANY_BMW.getName()).companyNation("독일")
            .carYearStart(2023).carYearEnd(2025).build();
    // 테슬라
    static RefCarInfo refCarInfo43= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_MODEL_3.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_MODEL3_1G.getName())
            .carSize(CarSize.SMALL_MIDDLE).company(CarCompany.CAR_COMPANY_TESLA.getName()).companyNation("미국")
            .carYearStart(2017).carYearEnd(2025).build();
    static RefCarInfo refCarInfo44= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_MODEL_S.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_MODELS_1G.getName())
            .carSize(CarSize.MIDDLE_LARGE).company(CarCompany.CAR_COMPANY_TESLA.getName()).companyNation("미국")
            .carYearStart(2022).carYearEnd(2025).build();
    static RefCarInfo refCarInfo45= RefCarInfo.builder().carCategory(CarCategory.CAR_IMPORTED.getName())
            .carModel(CarModel.CAR_MODEL_MODEL_Y.getName()).carDetailModel(CarDetailModel.CAR_DETAIL_MODEL_MODELY_1G.getName())
            .carSize(CarSize.MIDDLE_LARGE).company(CarCompany.CAR_COMPANY_TESLA.getName()).companyNation("미국")
            .carYearStart(2023).carYearEnd(2025).build();

    public List<RefCarInfo> listRefCarInfo = new ArrayList<>();

    public RefCarInfoBuilder(){
        listRefCarInfo.addAll(List.of(
                refCarInfo1,refCarInfo2,refCarInfo3,refCarInfo4,refCarInfo5,refCarInfo6,
                refCarInfo7,refCarInfo8,refCarInfo9,refCarInfo10,refCarInfo11,refCarInfo12,
                refCarInfo13,refCarInfo14,refCarInfo15,refCarInfo16,refCarInfo17,refCarInfo18,
                refCarInfo19,refCarInfo20,refCarInfo21,refCarInfo22,refCarInfo23,refCarInfo24,
                refCarInfo25,refCarInfo26,refCarInfo27,refCarInfo28,refCarInfo29,refCarInfo30,
                refCarInfo31,refCarInfo32,refCarInfo33,refCarInfo34,refCarInfo35,refCarInfo36,
                refCarInfo37,refCarInfo38,refCarInfo39,refCarInfo40,refCarInfo41,refCarInfo42,
                refCarInfo43,refCarInfo44,refCarInfo45
        ));
    }

}
