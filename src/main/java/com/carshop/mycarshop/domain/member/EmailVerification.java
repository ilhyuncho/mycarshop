package com.carshop.mycarshop.domain.member;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emailVerifications")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerification {

    @Id
    @GeneratedValue(generator = "UUID_GENERATOR")
    @GenericGenerator(name="UUID_GENERATOR", strategy = "org.hibernate.id.UUIDGenerator")
    private String verificationid;

    private String memberId;

}
