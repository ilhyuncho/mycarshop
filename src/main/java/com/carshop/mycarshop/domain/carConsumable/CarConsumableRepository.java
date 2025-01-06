package com.carshop.mycarshop.domain.carConsumable;

import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.carConsumable.search.CarConsumableSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarConsumableRepository extends JpaRepository<CarConsumable, Long>, CarConsumableSearch {


    @EntityGraph(attributePaths = "refCarConsumable")   // fetch Type 을 EAGER 설정해서 같이 가져옴
    List<CarConsumable> findByCar(Car car);


//    List<CarConsumable> findByCarAndRefCarConsumable(Car car, RefCarConsumable refCarConsumable);

    @EntityGraph(attributePaths = "refCarConsumable")   // fetch Type 을 EAGER 설정해서 같이 가져옴
    @Query("SELECT c FROM CarConsumable c WHERE c.car.carId = :carId AND c.refCarConsumable.refConsumableId = :refCarConsumableId")
    List<CarConsumable> findByCarAndRefCarConsumable(Long carId, Long refCarConsumableId);


    @Query("SELECT c FROM CarConsumable c WHERE c.car = (:car) AND c.consumableType IN (:consumableTypes)")
    List<CarConsumable> findByConsumableTypes(@Param("car") Car car, @Param("consumableTypes") List<ConsumableType> consumableTypes);
}
