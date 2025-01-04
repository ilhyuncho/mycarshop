package com.carshop.mycarshop.domain.user;

import com.carshop.mycarshop.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMember(Member member);

    @Query(value = "select u.* from users as u where u.MEMBER_ID = ?1", nativeQuery = true)
    Optional<User> findByMemberId(String memberId);
}
