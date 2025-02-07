package com.carshop.mycarshop.domain.user;

import com.carshop.mycarshop.domain.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
@Table(name="userAlarms")
public class UserAlarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userAlarmId")
    private Long userAlarmId;

    private String alarmTitle;
    private String alarmContent;

    private boolean alarmCheck;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId")   // pk(외래키)가 user테이블(주테이블)에 생성
    private User user;

    public void readAlarm(){
        if(!this.isAlarmCheck()){
            // 읽음으로 표시
            this.alarmCheck = true;
        }
    }

}