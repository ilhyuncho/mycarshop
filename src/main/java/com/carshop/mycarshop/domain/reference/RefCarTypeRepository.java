package com.carshop.mycarshop.domain.reference;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefCarTypeRepository extends JpaRepository<RefCarType, Long> {

    List<RefCarType> findByParentTypeId(int parentGroupIndex);
}