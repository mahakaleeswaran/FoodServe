package com.demo.foodserve.Repository;

import com.demo.foodserve.entity.FoodEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity,Integer> {
}
