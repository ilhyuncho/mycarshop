package com.carshop.mycarshop.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCreditRepository extends JpaRepository<UserCredit, Long> {


    Optional<UserCredit> findByUser(User user);
}