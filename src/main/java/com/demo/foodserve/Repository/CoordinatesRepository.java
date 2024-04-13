package com.demo.foodserve.Repository;

import com.demo.foodserve.entity.CoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepository extends JpaRepository<CoordinateEntity,Integer> {
}
