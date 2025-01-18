package com.carshop.mycarshop.testData.builder;

import com.carshop.mycarshop.domain.reference.RefCarType;

import java.util.ArrayList;
import java.util.List;

public class RefCarTypeInfoBuilder {
    // GROUP 0
    static RefCarType refCarType1 = RefCarType.builder().typeId(1).groupIndex(1).typeName("국산").parentTypeId(0).order(1).build();
    static RefCarType refCarType2 = RefCarType.builder().typeId(2).groupIndex(1).typeName("수입").parentTypeId(0).order(2).build();
    // GROUP 1
    static RefCarType refCarType3 = RefCarType.builder().typeId(3).groupIndex(2).typeName("현대").parentTypeId(1).order(1).build();
    static RefCarType refCarType4 = RefCarType.builder().typeId(4).groupIndex(2).typeName("기아").parentTypeId(1).order(2).build();
    static RefCarType refCarType5 = RefCarType.builder().typeId(5).groupIndex(2).typeName("kgm").parentTypeId(1).order(3).build();
    // GROUP 2
    static RefCarType refCarType6 = RefCarType.builder().typeId(6).groupIndex(2).typeName("벤츠").parentTypeId(2).order(1).build();
    static RefCarType refCarType7 = RefCarType.builder().typeId(7).groupIndex(2).typeName("bmw").parentTypeId(2).order(2).build();
    static RefCarType refCarType8 = RefCarType.builder().typeId(8).groupIndex(2).typeName("테슬라").parentTypeId(2).order(3).build();
    // GROUP 3
    static RefCarType refCarType9 = RefCarType.builder().typeId(9).groupIndex(3).typeName("쏘나타").parentTypeId(3).order(1).build();
    static RefCarType refCarType10 = RefCarType.builder().typeId(10).groupIndex(3).typeName("투싼").parentTypeId(3).order(2).build();
    static RefCarType refCarType11 = RefCarType.builder().typeId(11).groupIndex(3).typeName("아반떼").parentTypeId(3).order(3).build();

    static RefCarType refCarType12 = RefCarType.builder().typeId(12).groupIndex(3).typeName("k5").parentTypeId(4).order(1).build();
    static RefCarType refCarType13 = RefCarType.builder().typeId(13).groupIndex(3).typeName("k7").parentTypeId(4).order(2).build();
    static RefCarType refCarType14 = RefCarType.builder().typeId(14).groupIndex(3).typeName("k8").parentTypeId(4).order(3).build();
    static RefCarType refCarType15 = RefCarType.builder().typeId(15).groupIndex(3).typeName("스포티지").parentTypeId(4).order(4).build();

    static RefCarType refCarType16 = RefCarType.builder().typeId(16).groupIndex(3).typeName("A-CLASS").parentTypeId(6).order(1).build();
    static RefCarType refCarType17 = RefCarType.builder().typeId(17).groupIndex(3).typeName("C-CLASS").parentTypeId(6).order(1).build();
    static RefCarType refCarType18 = RefCarType.builder().typeId(18).groupIndex(3).typeName("E-CLASS").parentTypeId(6).order(2).build();
    static RefCarType refCarType19 = RefCarType.builder().typeId(19).groupIndex(3).typeName("S-CLASS").parentTypeId(6).order(3).build();
    static RefCarType refCarType20 = RefCarType.builder().typeId(20).groupIndex(3).typeName("3-SERIES").parentTypeId(7).order(1).build();
    static RefCarType refCarType21 = RefCarType.builder().typeId(21).groupIndex(3).typeName("5-SERIES").parentTypeId(7).order(2).build();
    static RefCarType refCarType22 = RefCarType.builder().typeId(22).groupIndex(3).typeName("7-SERIES").parentTypeId(7).order(3).build();
    static RefCarType refCarType23 = RefCarType.builder().typeId(23).groupIndex(3).typeName("X5").parentTypeId(7).order(4).build();

    static RefCarType refCarType24 = RefCarType.builder().typeId(24).groupIndex(3).typeName("MODEL3").parentTypeId(8).order(1).build();
    static RefCarType refCarType25 = RefCarType.builder().typeId(25).groupIndex(3).typeName("MODELS").parentTypeId(8).order(2).build();
    static RefCarType refCarType26 = RefCarType.builder().typeId(26).groupIndex(3).typeName("MODELY").parentTypeId(8).order(3).build();


    // GROUP 4
    static RefCarType refCarType27 = RefCarType.builder().typeId(27).groupIndex(4).typeName("쏘나타 디 엣지").parentTypeId(9).order(1).build();
    static RefCarType refCarType28 = RefCarType.builder().typeId(28).groupIndex(4).typeName("쏘나타 하이브리드").parentTypeId(9).order(2).build();
    static RefCarType refCarType29 = RefCarType.builder().typeId(29).groupIndex(4).typeName("LF 쏘나타").parentTypeId(9).order(3).build();
    static RefCarType refCarType30 = RefCarType.builder().typeId(30).groupIndex(4).typeName("더 뉴 투싼").parentTypeId(10).order(1).build();
    static RefCarType refCarType31 = RefCarType.builder().typeId(31).groupIndex(4).typeName("올 뉴 투싼").parentTypeId(10).order(2).build();
    static RefCarType refCarType32 = RefCarType.builder().typeId(32).groupIndex(4).typeName("투싼").parentTypeId(10).order(3).build();

    static RefCarType refCarType33 = RefCarType.builder().typeId(33).groupIndex(4).typeName("더 뉴 아반떼").parentTypeId(11).order(1).build();
    static RefCarType refCarType34 = RefCarType.builder().typeId(34).groupIndex(4).typeName("아반떼 AD").parentTypeId(11).order(2).build();
    static RefCarType refCarType35 = RefCarType.builder().typeId(35).groupIndex(4).typeName("올 뉴 아반떼").parentTypeId(11).order(3).build();


    static RefCarType refCarType36 = RefCarType.builder().typeId(36).groupIndex(4).typeName("k5 DH").parentTypeId(12).order(1).build();
    static RefCarType refCarType37 = RefCarType.builder().typeId(37).groupIndex(4).typeName("NEW k5").parentTypeId(12).order(2).build();
    static RefCarType refCarType38 = RefCarType.builder().typeId(38).groupIndex(4).typeName("ALL NEW k5").parentTypeId(12).order(3).build();

    static RefCarType refCarType39 = RefCarType.builder().typeId(39).groupIndex(4).typeName("k7 AH").parentTypeId(13).order(1).build();
    static RefCarType refCarType40 = RefCarType.builder().typeId(40).groupIndex(4).typeName("NEW k7").parentTypeId(13).order(2).build();
    static RefCarType refCarType41 = RefCarType.builder().typeId(41).groupIndex(4).typeName("ALL NEW k7").parentTypeId(13).order(3).build();

    static RefCarType refCarType42 = RefCarType.builder().typeId(42).groupIndex(4).typeName("k8 SD").parentTypeId(14).order(1).build();

    static RefCarType refCarType43 = RefCarType.builder().typeId(43).groupIndex(4).typeName("스포티지").parentTypeId(15).order(1).build();
    static RefCarType refCarType44 = RefCarType.builder().typeId(44).groupIndex(4).typeName("THE NEW 스포티지").parentTypeId(15).order(2).build();
    static RefCarType refCarType45 = RefCarType.builder().typeId(45).groupIndex(4).typeName("ALL NEW 스포티지").parentTypeId(15).order(3).build();

    static RefCarType refCarType46 = RefCarType.builder().typeId(46).groupIndex(4).typeName("A-CLASS 5세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType47 = RefCarType.builder().typeId(47).groupIndex(4).typeName("A-CLASS 6세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType48 = RefCarType.builder().typeId(48).groupIndex(4).typeName("A-CLASS 7세대").parentTypeId(16).order(3).build();

    static RefCarType refCarType49 = RefCarType.builder().typeId(49).groupIndex(4).typeName("C-CLASS 4세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType50 = RefCarType.builder().typeId(50).groupIndex(4).typeName("C-CLASS 5세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType51 = RefCarType.builder().typeId(51).groupIndex(4).typeName("C-CLASS 6세대").parentTypeId(16).order(3).build();

    static RefCarType refCarType52 = RefCarType.builder().typeId(52).groupIndex(4).typeName("E-CLASS 7세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType53 = RefCarType.builder().typeId(53).groupIndex(4).typeName("E-CLASS 8세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType54 = RefCarType.builder().typeId(54).groupIndex(4).typeName("E-CLASS 9세대").parentTypeId(16).order(3).build();

    static RefCarType refCarType55 = RefCarType.builder().typeId(55).groupIndex(4).typeName("S-CLASS 7세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType56 = RefCarType.builder().typeId(56).groupIndex(4).typeName("S-CLASS 8세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType57 = RefCarType.builder().typeId(57).groupIndex(4).typeName("S-CLASS 9세대").parentTypeId(16).order(3).build();

    static RefCarType refCarType58 = RefCarType.builder().typeId(58).groupIndex(4).typeName("3-SERIES 5세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType59 = RefCarType.builder().typeId(59).groupIndex(4).typeName("3-SERIES 6세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType60 = RefCarType.builder().typeId(60).groupIndex(4).typeName("3-SERIES 7세대").parentTypeId(16).order(3).build();

    static RefCarType refCarType61 = RefCarType.builder().typeId(61).groupIndex(4).typeName("5-SERIES 6세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType62 = RefCarType.builder().typeId(62).groupIndex(4).typeName("5-SERIES 7세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType63 = RefCarType.builder().typeId(63).groupIndex(4).typeName("5-SERIES 8세대").parentTypeId(16).order(3).build();

    static RefCarType refCarType64 = RefCarType.builder().typeId(64).groupIndex(4).typeName("7-SERIES 4세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType65 = RefCarType.builder().typeId(65).groupIndex(4).typeName("7-SERIES 5세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType66 = RefCarType.builder().typeId(66).groupIndex(4).typeName("7-SERIES 6세대").parentTypeId(16).order(3).build();

    static RefCarType refCarType67 = RefCarType.builder().typeId(67).groupIndex(4).typeName("X5 1세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType68 = RefCarType.builder().typeId(68).groupIndex(4).typeName("X5 2세대").parentTypeId(16).order(2).build();

    static RefCarType refCarType69 = RefCarType.builder().typeId(69).groupIndex(4).typeName("MODEL3 1세대").parentTypeId(16).order(1).build();
    static RefCarType refCarType70 = RefCarType.builder().typeId(70).groupIndex(4).typeName("MODELS 1세대").parentTypeId(16).order(2).build();
    static RefCarType refCarType71 = RefCarType.builder().typeId(71).groupIndex(4).typeName("MODELY 1세대").parentTypeId(16).order(3).build();


    public List<RefCarType> listRefCarType = new ArrayList<>();


    public RefCarTypeInfoBuilder(){
        listRefCarType.addAll(List.of(refCarType1, refCarType2, refCarType3, refCarType4, refCarType5, refCarType6,
                refCarType7, refCarType8, refCarType9, refCarType10, refCarType11, refCarType12,
                refCarType13, refCarType14, refCarType15, refCarType16, refCarType17, refCarType18,
                refCarType19, refCarType20, refCarType21, refCarType22, refCarType23, refCarType24,
                refCarType25, refCarType26, refCarType27, refCarType28, refCarType29, refCarType30,
                refCarType31, refCarType32, refCarType33, refCarType34, refCarType35, refCarType36,
                refCarType37, refCarType38, refCarType39, refCarType40, refCarType41, refCarType42,
                refCarType43, refCarType44, refCarType45, refCarType46, refCarType47, refCarType48,
                refCarType49, refCarType50, refCarType51, refCarType52, refCarType53, refCarType54,
                refCarType55, refCarType56, refCarType57, refCarType58, refCarType59, refCarType60,
                refCarType61, refCarType62, refCarType63, refCarType64, refCarType65, refCarType66,
                refCarType67, refCarType68, refCarType69, refCarType70, refCarType71
        ));
    }

}

