package com.demo.foodserve.Repository;

import com.demo.foodserve.entity.CredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<CredentialsEntity,Integer> {
}
