package com.demo.foodserve.Repository;

import com.demo.foodserve.entity.DonorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<DonorEntity,Integer> {
}
