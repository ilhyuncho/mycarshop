package com.carshop.mycarshop.testData.builder;

import com.carshop.mycarshop.domain.reference.RefCarGrade;
import com.carshop.mycarshop.domain.reference.RefCarTrim;
import com.carshop.mycarshop.domain.reference.carType.CarDetailModel;
import com.carshop.mycarshop.domain.reference.carType.CarFuelType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class RefCarDataBuilder {

    static RefCarTrim refCarTrim1 = RefCarTrim.builder().name("프레스티지").nameDesc("프레스티지").build();
    static RefCarTrim refCarTrim2 = RefCarTrim.builder().name("스마트").nameDesc("스마트").build();
    static RefCarTrim refCarTrim3 = RefCarTrim.builder().name("모던").nameDesc("모던").build();

    // 벤츠
    static RefCarTrim refCarTrim10001 = RefCarTrim.builder().name("e200 아방가르드").nameDesc("e200 아방가르드").build();
    static RefCarTrim refCarTrim10002 = RefCarTrim.builder().name("e300 4matic 익스클루시브").nameDesc("e300 4matic 익스클루시브").build();
    static RefCarTrim refCarTrim10003 = RefCarTrim.builder().name("e300 4matic AMG LINE").nameDesc("e300 4matic AMG LINE").build();
    static RefCarTrim refCarTrim10004 = RefCarTrim.builder().name("e300 4matic AMG LINE 프리미어 스페셜").nameDesc("e300 4matic AMG LINE 프리미어 스페셜").build();
    static RefCarTrim refCarTrim10005 = RefCarTrim.builder().name("e450 4matic").nameDesc("e450 4matic").build();
    static RefCarTrim refCarTrim10006 = RefCarTrim.builder().name("e450 4matic 익스클루시브").nameDesc("e450 4matic 익스클루시브").build();
    static RefCarTrim refCarTrim10007 = RefCarTrim.builder().name("e250d 4matic 익스클루시브").nameDesc("e250d 4matic 익스클루시브").build();
    static RefCarTrim refCarTrim10008 = RefCarTrim.builder().name("e250d 4matic AMG LINE").nameDesc("e250d 4matic AMG LINE").build();

    // BMW
    static RefCarTrim refCarTrim20001 = RefCarTrim.builder().name("320i").nameDesc("320i").build();
    static RefCarTrim refCarTrim20002 = RefCarTrim.builder().name("320i m 스포츠").nameDesc("320i m 스포츠").build();
    static RefCarTrim refCarTrim20003 = RefCarTrim.builder().name("320i 스페셜 에디션").nameDesc("320i 스페셜 에디션").build();
    static RefCarTrim refCarTrim20004 = RefCarTrim.builder().name("330i xDrive").nameDesc("330i xDrive").build();
    static RefCarTrim refCarTrim20005 = RefCarTrim.builder().name("330i xDireve m 스포츠").nameDesc("330i xDireve m 스포츠").build();
    static RefCarTrim refCarTrim20006 = RefCarTrim.builder().name("330i xDrive m 스포츠 프로 스페셜").nameDesc("330i xDrive m 스포츠 프로 스페셜").build();
    static RefCarTrim refCarTrim20007 = RefCarTrim.builder().name("323d").nameDesc("323d").build();
    static RefCarTrim refCarTrim20008 = RefCarTrim.builder().name("323d m 스포츠").nameDesc("323d m 스포츠").build();
    static RefCarTrim refCarTrim20009 = RefCarTrim.builder().name("323d xDrive").nameDesc("323d xDrive").build();
    static RefCarTrim refCarTrim20010 = RefCarTrim.builder().name("323d xDrive m 스포츠").nameDesc("323d xDrive m 스포츠").build();

    static RefCarTrim refCarTrim20101 = RefCarTrim.builder().name("520i").nameDesc("520i").build();
    static RefCarTrim refCarTrim20102 = RefCarTrim.builder().name("520i m 스포츠").nameDesc("520i m 스포츠").build();
    static RefCarTrim refCarTrim20103 = RefCarTrim.builder().name("520i 스페셜 에디션").nameDesc("520i 스페셜 에디션").build();
    static RefCarTrim refCarTrim20104 = RefCarTrim.builder().name("530i xDrive").nameDesc("530i xDrive").build();
    static RefCarTrim refCarTrim20105 = RefCarTrim.builder().name("530i xDireve m 스포츠").nameDesc("530i xDireve m 스포츠").build();
    static RefCarTrim refCarTrim20106 = RefCarTrim.builder().name("530i xDrive m 스포츠 프로 스페셜").nameDesc("530i xDrive m 스포츠 프로 스페셜").build();
    static RefCarTrim refCarTrim20107 = RefCarTrim.builder().name("523d").nameDesc("523d").build();
    static RefCarTrim refCarTrim20108 = RefCarTrim.builder().name("523d m 스포츠").nameDesc("523d m 스포츠").build();
    static RefCarTrim refCarTrim20109 = RefCarTrim.builder().name("523d xDrive").nameDesc("523d xDrive").build();
    static RefCarTrim refCarTrim20110 = RefCarTrim.builder().name("523d xDrive m 스포츠").nameDesc("523d xDrive m 스포츠").build();
    static RefCarTrim refCarTrim20111 = RefCarTrim.builder().name("530e").nameDesc("530e").build();
    static RefCarTrim refCarTrim20112 = RefCarTrim.builder().name("530e m 스포츠").nameDesc("530e m 스포츠").build();

    // 테슬라
    static RefCarTrim refCarTrim30001 = RefCarTrim.builder().name("RWD").nameDesc("RWD").build();
    static RefCarTrim refCarTrim30002 = RefCarTrim.builder().name("스탠다드 레인지 플러스").nameDesc("스탠다드 레인지 플러스").build();
    static RefCarTrim refCarTrim30003 = RefCarTrim.builder().name("롱 레인지").nameDesc("롱 레인지").build();
    static RefCarTrim refCarTrim30004 = RefCarTrim.builder().name("퍼포먼스").nameDesc("퍼포먼스").build();
    static RefCarTrim refCarTrim30005 = RefCarTrim.builder().name("75D").nameDesc("75D").build();
    static RefCarTrim refCarTrim30006 = RefCarTrim.builder().name("90D").nameDesc("90D").build();
    static RefCarTrim refCarTrim30007 = RefCarTrim.builder().name("플래드").nameDesc("플래드").build();


    // 쏘나타 디 엣지
    static RefCarGrade refCarGrade1 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_EDGE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade2 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_EDGE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade3 = new RefCarGrade("2.5", "2.5d 디젤", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_EDGE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade4 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_EDGE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade5 = new RefCarGrade("2.0", "2.0 LPG", CarFuelType.FUEL_LPG,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_EDGE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // 쏘나타 뉴 라이즈
    static RefCarGrade refCarGrade6 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_NEW_RISE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade7 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_NEW_RISE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade8 = new RefCarGrade("2.5", "2.5d 디젤", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_NEW_RISE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade9 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_NEW_RISE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade10 = new RefCarGrade("2.0", "2.0 LPG", CarFuelType.FUEL_LPG,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_NEW_RISE, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // 쏘나타 LF
    static RefCarGrade refCarGrade11 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_LF, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade12 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_LF, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade13 = new RefCarGrade("2.5", "2.5d 디젤", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_LF, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade14 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_LF, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade15 = new RefCarGrade("2.0", "2.0 LPG", CarFuelType.FUEL_LPG,
            CarDetailModel.CAR_DETAIL_MODEL_SONATA_LF, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // 아반테 AD
    static RefCarGrade refCarGrade101 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_AD, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade102 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_AD, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade103 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_AD, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // 더 뉴 아반떼
    static RefCarGrade refCarGrade104 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_THE_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade105 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_THE_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade106 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_THE_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // 올 뉴 아반떼
    static RefCarGrade refCarGrade107 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade108 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade109 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_AVANTE_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // K5 DH
    static RefCarGrade refCarGrade1001 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1002 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1003 = new RefCarGrade("2.5", "2.5d 디젤", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1004 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_K5_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1005 = new RefCarGrade("2.0", "2.0 LPG", CarFuelType.FUEL_LPG,
            CarDetailModel.CAR_DETAIL_MODEL_K5_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // NEW K5
    static RefCarGrade refCarGrade1011 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1012 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1013 = new RefCarGrade("2.5", "2.5d 디젤", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1014 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_K5_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1015 = new RefCarGrade("2.0", "2.0 LPG", CarFuelType.FUEL_LPG,
            CarDetailModel.CAR_DETAIL_MODEL_K5_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // K5_ALL_NEW
    static RefCarGrade refCarGrade1021 = new RefCarGrade("1.6", "1.6 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1022 = new RefCarGrade("2.0", "2.0 가솔린", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1023 = new RefCarGrade("2.5", "2.5d 디젤", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1024 = new RefCarGrade("2.0", "2.0 하이브리드", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1025 = new RefCarGrade("2.0", "2.0 LPG", CarFuelType.FUEL_LPG,
            CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));

    // 스포티지 DH
    static RefCarGrade refCarGrade1031 = new RefCarGrade("1.6 2WD", "1.6 터보", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1032 = new RefCarGrade("1.6 4WD", "1.6 터보", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1033 = new RefCarGrade("2.0d 2WD", "2.0d 디젤", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1034 = new RefCarGrade("2.0d 4WD", "2.0d 디젤", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));
    static RefCarGrade refCarGrade1035 = new RefCarGrade("2.0 2WD", "2.0 LPG ", CarFuelType.FUEL_LPG,
            CarDetailModel.CAR_DETAIL_MODEL_SPORTAGE_DH, Set.of(refCarTrim1, refCarTrim2, refCarTrim3));



    // 벤츠 - E-CLASS 5세대
    static RefCarGrade refCarGrade10001 = new RefCarGrade("e200", "e200", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_5G, Set.of(refCarTrim10001));
    static RefCarGrade refCarGrade10002 = new RefCarGrade("e300", "e300", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_5G, Set.of(refCarTrim10002, refCarTrim10003, refCarTrim10004));
    static RefCarGrade refCarGrade10003 = new RefCarGrade("e450", "e450", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_5G, Set.of(refCarTrim10005, refCarTrim10006));
    static RefCarGrade refCarGrade10004 = new RefCarGrade("e250d", "e250d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_5G, Set.of(refCarTrim10007, refCarTrim10008));

    // 벤츠 - E-CLASS 6세대
    static RefCarGrade refCarGrade10011 = new RefCarGrade("e200", "e200", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_6G, Set.of(refCarTrim10001));
    static RefCarGrade refCarGrade10012 = new RefCarGrade("e300", "e300", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_6G, Set.of(refCarTrim10002, refCarTrim10003, refCarTrim10004));
    static RefCarGrade refCarGrade10013 = new RefCarGrade("e450", "e450", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_6G, Set.of(refCarTrim10005, refCarTrim10006));
    static RefCarGrade refCarGrade10014 = new RefCarGrade("e250d", "e250d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_6G, Set.of(refCarTrim10007, refCarTrim10008));

    // 벤츠 - E-CLASS 7세대
    static RefCarGrade refCarGrade10021 = new RefCarGrade("e200", "e200", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_7G, Set.of(refCarTrim10001));
    static RefCarGrade refCarGrade10022 = new RefCarGrade("e300", "e300", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_7G, Set.of(refCarTrim10002, refCarTrim10003, refCarTrim10004));
    static RefCarGrade refCarGrade10023 = new RefCarGrade("e450", "e450", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_7G, Set.of(refCarTrim10005, refCarTrim10006));
    static RefCarGrade refCarGrade10024 = new RefCarGrade("e250d", "e250d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_E_CLASS_7G, Set.of(refCarTrim10007, refCarTrim10008));

    // 벤츠 - C-CLASS 4세대
    static RefCarGrade refCarGrade10101 = new RefCarGrade("e200", "e200", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_4G, Set.of(refCarTrim10001));
    static RefCarGrade refCarGrade10102 = new RefCarGrade("e300", "e300", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_4G, Set.of(refCarTrim10002, refCarTrim10003, refCarTrim10004));
    // 벤츠 - C-CLASS 5세대
    static RefCarGrade refCarGrade10111 = new RefCarGrade("e200", "e200", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_5G, Set.of(refCarTrim10001));
    static RefCarGrade refCarGrade10112 = new RefCarGrade("e300", "e300", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_5G, Set.of(refCarTrim10002, refCarTrim10003, refCarTrim10004));
    // 벤츠 - C-CLASS 6세대
    static RefCarGrade refCarGrade10121 = new RefCarGrade("e200", "e200", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_6G, Set.of(refCarTrim10001));
    static RefCarGrade refCarGrade10122 = new RefCarGrade("e300", "e300", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_6G, Set.of(refCarTrim10002, refCarTrim10003, refCarTrim10004));

    // BMW 3시리즈
    static RefCarGrade refCarGrade20001 = new RefCarGrade("320i", "320i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_5G, Set.of(refCarTrim20001, refCarTrim20002, refCarTrim20003));
    static RefCarGrade refCarGrade20002 = new RefCarGrade("330i", "330i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_5G, Set.of(refCarTrim20004, refCarTrim20005, refCarTrim20006));
    static RefCarGrade refCarGrade20003 = new RefCarGrade("323d", "323d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_5G, Set.of(refCarTrim20007, refCarTrim20008, refCarTrim20009, refCarTrim20010));
    static RefCarGrade refCarGrade20011 = new RefCarGrade("320i", "320i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_6G, Set.of(refCarTrim20001, refCarTrim20002, refCarTrim20003));
    static RefCarGrade refCarGrade20012 = new RefCarGrade("330i", "330i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_6G, Set.of(refCarTrim20004, refCarTrim20005, refCarTrim20006));
    static RefCarGrade refCarGrade20013 = new RefCarGrade("323d", "323d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_6G, Set.of(refCarTrim20007, refCarTrim20008, refCarTrim20009, refCarTrim20010));
    static RefCarGrade refCarGrade20021 = new RefCarGrade("320i", "320i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_7G, Set.of(refCarTrim20001, refCarTrim20002, refCarTrim20003));
    static RefCarGrade refCarGrade20022 = new RefCarGrade("330i", "330i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_7G, Set.of(refCarTrim20004, refCarTrim20005, refCarTrim20006));
    static RefCarGrade refCarGrade20023 = new RefCarGrade("323d", "323d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_7G, Set.of(refCarTrim20007, refCarTrim20008, refCarTrim20009, refCarTrim20010));

    // BMW 5시리즈
    static RefCarGrade refCarGrade20101 = new RefCarGrade("520i", "520i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_6G, Set.of(refCarTrim20101, refCarTrim20102, refCarTrim20103));
    static RefCarGrade refCarGrade20102 = new RefCarGrade("530i", "530i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_6G, Set.of(refCarTrim20104, refCarTrim20105, refCarTrim20106));
    static RefCarGrade refCarGrade20103 = new RefCarGrade("523d", "523d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_6G, Set.of(refCarTrim20107, refCarTrim20108, refCarTrim20109, refCarTrim20110));
    static RefCarGrade refCarGrade20104 = new RefCarGrade("530e", "530e", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_6G, Set.of(refCarTrim20111, refCarTrim20112));

    static RefCarGrade refCarGrade20111 = new RefCarGrade("520i", "520i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_7G, Set.of(refCarTrim20101, refCarTrim20102, refCarTrim20103));
    static RefCarGrade refCarGrade20112 = new RefCarGrade("530i", "530i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_7G, Set.of(refCarTrim20104, refCarTrim20105, refCarTrim20106));
    static RefCarGrade refCarGrade20113 = new RefCarGrade("523d", "523d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_7G, Set.of(refCarTrim20107, refCarTrim20108, refCarTrim20109, refCarTrim20110));
    static RefCarGrade refCarGrade20114 = new RefCarGrade("530e", "530e", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_7G, Set.of(refCarTrim20111, refCarTrim20112));

    static RefCarGrade refCarGrade20121 = new RefCarGrade("520i", "520i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_8G, Set.of(refCarTrim20101, refCarTrim20102, refCarTrim20103));
    static RefCarGrade refCarGrade20122 = new RefCarGrade("530i", "530i", CarFuelType.FUEL_GASOLINE,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_8G, Set.of(refCarTrim20104, refCarTrim20105, refCarTrim20106));
    static RefCarGrade refCarGrade20123 = new RefCarGrade("523d", "523d", CarFuelType.FUEL_DIESEL,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_8G, Set.of(refCarTrim20107, refCarTrim20108, refCarTrim20109, refCarTrim20110));
    static RefCarGrade refCarGrade20124 = new RefCarGrade("530e", "530e", CarFuelType.FUEL_HYBRID,
            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_8G, Set.of(refCarTrim20111, refCarTrim20112));

    // 테슬라
    static RefCarGrade refCarGrade30001 = new RefCarGrade("전기", "전기", CarFuelType.FUEL_ELECTRIC,
            CarDetailModel.CAR_DETAIL_MODEL_MODEL3_1G, Set.of(refCarTrim30001, refCarTrim30002, refCarTrim30003, refCarTrim30004));
    static RefCarGrade refCarGrade30002 = new RefCarGrade("전기", "전기", CarFuelType.FUEL_ELECTRIC,
            CarDetailModel.CAR_DETAIL_MODEL_MODELS_1G, Set.of(refCarTrim30005, refCarTrim30006, refCarTrim30007, refCarTrim30003, refCarTrim30004));
    static RefCarGrade refCarGrade30003 = new RefCarGrade("전기", "전기", CarFuelType.FUEL_ELECTRIC,
            CarDetailModel.CAR_DETAIL_MODEL_MODELY_1G, Set.of(refCarTrim30005, refCarTrim30006, refCarTrim30007, refCarTrim30003, refCarTrim30004));


//    static RefCarGrade refCarGrade13 = new RefCarGrade("e450", "e450", CarFuelType.FUEL_GASOLINE,
//            CarDetailModel.CAR_DETAIL_MODEL_C_CLASS_4G, Set.of(refCarTrim4, refCarTrim5));
//
//
//    static RefCarGrade refCarGrade11 = new RefCarGrade("e450", "e450", CarFuelType.FUEL_GASOLINE,
//            CarDetailModel.CAR_DETAIL_MODEL_3SERIES_5G, Set.of(refCarTrim4, refCarTrim5));
//    static RefCarGrade refCarGrade12 = new RefCarGrade("e450", "e450", CarFuelType.FUEL_GASOLINE,
//            CarDetailModel.CAR_DETAIL_MODEL_5SERIES_6G, Set.of(refCarTrim4, refCarTrim5));
//
//    static RefCarGrade refCarGrade14 = new RefCarGrade("e450", "e450", CarFuelType.FUEL_GASOLINE,
//            CarDetailModel.CAR_DETAIL_MODEL_K5_ALL_NEW, Set.of(refCarTrim4, refCarTrim5));




    public List<RefCarGrade> listRefGrade = new ArrayList<>();

    public RefCarDataBuilder(){
        listRefGrade.addAll(List.of(refCarGrade1, refCarGrade2, refCarGrade3, refCarGrade4, refCarGrade5, refCarGrade6, refCarGrade7,
                refCarGrade8, refCarGrade9, refCarGrade10, refCarGrade11, refCarGrade12, refCarGrade13, refCarGrade14, refCarGrade15,

                refCarGrade101, refCarGrade102, refCarGrade103, refCarGrade104, refCarGrade105, refCarGrade106, refCarGrade107,
                refCarGrade108, refCarGrade109,

                refCarGrade1001, refCarGrade1002, refCarGrade1003, refCarGrade1004, refCarGrade1005,
                refCarGrade1011, refCarGrade1012, refCarGrade1013, refCarGrade1014, refCarGrade1015,
                refCarGrade1021, refCarGrade1022, refCarGrade1023, refCarGrade1024, refCarGrade1025,
                refCarGrade1031, refCarGrade1032, refCarGrade1033, refCarGrade1034, refCarGrade1035,

                refCarGrade10001, refCarGrade10002, refCarGrade10003, refCarGrade10004, refCarGrade10011, refCarGrade10012, refCarGrade10013,
                refCarGrade10014, refCarGrade10021, refCarGrade10022, refCarGrade10023, refCarGrade10024,

                refCarGrade10101, refCarGrade10102,
                refCarGrade10111, refCarGrade10112,
                refCarGrade10121, refCarGrade10122,
                refCarGrade20001, refCarGrade20002, refCarGrade20003,
                refCarGrade20011, refCarGrade20012, refCarGrade20013,
                refCarGrade20021, refCarGrade20022, refCarGrade20023,
                refCarGrade20101, refCarGrade20102, refCarGrade20103, refCarGrade20104,
                refCarGrade20111, refCarGrade20112, refCarGrade20113, refCarGrade20114,
                refCarGrade20121, refCarGrade20122, refCarGrade20123, refCarGrade20124,

                refCarGrade30001, refCarGrade30002, refCarGrade30003
        ));
    }

}
