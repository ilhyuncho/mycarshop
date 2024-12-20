package com.carshop.mycarshop.domain.user;

import com.carshop.mycarshop.domain.common.BaseEntity;
import com.carshop.mycarshop.domain.sellingCar.SellingCar;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="UserSearchCarHistorys")
public class UserSearchCarHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userSearchCarHistoryId")
    private Long userSearchCarHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)   // 일단 @ManyToOne 단방향
    @JoinColumn(name="SELLINGCAR_ID")
    private SellingCar sellingCar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uId")
    private User user;

    private Integer searchCount;

    public void plusSearchCount(){
        this.searchCount++;
    }


}