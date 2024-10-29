package com.carshop.mycarshop.domain.carConsumable;

import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.carConsumable.search.CarConsumableSearch;
import com.carshop.mycarshop.domain.reference.RefCarConsumable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarConsumableRepository extends JpaRepository<CarConsumable, Long>, CarConsumableSearch {

    List<CarConsumable> findByCar(Car car);
    List<CarConsumable> findByCarAndRefCarConsumable(Car car, RefCarConsumable refCarConsumable);
    //List<CarConsumable> findByCarAndConsumableType(Car car, ConsumableType consumableType);

    @Query("SELECT c FROM CarConsumable c WHERE c.car = (:car) AND c.consumableType IN (:consumableTypes)")
    List<CarConsumable> findByConsumableTypes(@Param("car") Car car, @Param("consumableTypes") List<ConsumableType> consumableTypes);
}
