package com.carshop.mycarshop.domain.reference;

import com.carshop.mycarshop.domain.reference.RefCarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefCarInfoRepository extends JpaRepository<RefCarInfo, Long> {

    //List<RefCarInfo> findByParentTypeId(int parentGroupIndex);
}
