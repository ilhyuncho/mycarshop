package com.carshop.mycarshop.domain.sellingCar.search;

import com.carshop.mycarshop.domain.sellingCar.QSellingCar;
import com.carshop.mycarshop.domain.sellingCar.SellType;
import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import com.carshop.mycarshop.dto.PageRequestExtDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SellingCarSearchImpl extends QuerydslRepositorySupport implements SellingCarSearch {
    public SellingCarSearchImpl() {
        super(SellingCar.class);
    }


    @Override
    public Page<SellingCar> searchAll(Pageable pageable, PageRequestExtDTO pageRequestExtDT) {

        String[] types = pageRequestExtDT.getTypes();
        String[] typeExts = pageRequestExtDT.getTypeExts();
        String keyword = pageRequestExtDT.getKeyword();
        int carYearsMin = pageRequestExtDT.getCarYearsMin();
        int carYearsMax = pageRequestExtDT.getCarYearsMax();

        String typeGroup1 = pageRequestExtDT.getTypeGroup1();
        String typeGroup2 = pageRequestExtDT.getTypeGroup2();
        String typeGroup3 = pageRequestExtDT.getTypeGroup3();
        String typeGroup4 = pageRequestExtDT.getTypeGroup4();

        log.error(pageRequestExtDT);

        QSellingCar sellingCar = QSellingCar.sellingCar;
        JPQLQuery<SellingCar> query = from(sellingCar);

        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "m":       // 모델명
                        booleanBuilder.or(sellingCar.car.refCarInfo.carModel.contains(keyword));
                        break;
                    case "y":       // 연식
                        log.error("keyword: " + keyword);
                        booleanBuilder.or(sellingCar.car.carYears.eq(Integer.valueOf(keyword)));
                        break;
                }
            }
            query.where(booleanBuilder);
        }


        if (typeExts != null && typeExts.length > 0) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : typeExts) {
                switch (type) {
                    case "a":
                        booleanBuilder.or(sellingCar.sellType.eq(SellType.SELL_AUCTION));
                        break;
                    case "c":
                        booleanBuilder.or(sellingCar.sellType.eq(SellType.SELL_CONSULT));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        if(carYearsMin > 0 && carYearsMax > 0){
            query.where(sellingCar.car.carYears.between(carYearsMin, carYearsMax));                            // 선택 한 연식
        }

        if(null != typeGroup1){
            query.where(sellingCar.car.refCarInfo.carCategory.eq(typeGroup1));
        };
        if(null != typeGroup2){
            query.where(sellingCar.car.refCarInfo.company.eq(typeGroup2));
        }
        if(null != typeGroup3){
            query.where(sellingCar.car.refCarInfo.carModel.eq(typeGroup3));
        }
        if(null != typeGroup4){
            query.where(sellingCar.car.refCarInfo.carDetailModel.eq(typeGroup4));
        };

       // query.where(sellingCar.sellingCarStatus.eq(SellingCarStatus.PROCESSING));   // 판매 중 인것만 표시

        this.getQuerydsl().applyPagination(pageable, query);
        List<SellingCar> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}