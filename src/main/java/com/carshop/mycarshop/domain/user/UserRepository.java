package com.carshop.mycarshop.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMemberId(String memberId);

    void deleteByMemberId(String memberId);
}
