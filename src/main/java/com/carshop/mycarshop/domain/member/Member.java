package com.carshop.mycarshop.domain.member;

import com.carshop.mycarshop.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
@Table(name="members")
public class Member extends BaseEntity {

    @Id
    private String memberId;

    private String memberPw;
    private String email;
    private boolean isDel;

    private boolean isSocial;
    private boolean verified = true;       // 이메일 인증 유무

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String memberPw){
        this.memberPw = memberPw;
    }
    public void changeEmail(String email){
        this.email = email;
    }
    public void changeDel(boolean isDel){
        this.isDel = isDel;
    }
    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }
    public void clearRoles(){
        this.roleSet.clear();
    }
    public void changeSocial(boolean isSocial){
        this.isSocial = isSocial;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}