package com.demo.foodserve.Repository;

import com.demo.foodserve.entity.RecieverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecieverRespository extends JpaRepository<RecieverEntity,Integer> {
}
