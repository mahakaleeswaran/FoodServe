package com.demo.foodserve.Repository;

import com.demo.foodserve.entity.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBackEntity,Integer> {
}
