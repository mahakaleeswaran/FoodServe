package com.demo.foodserve.Repository;

import com.demo.foodserve.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    List<PostEntity> findAllByCreatedDateBetween(Date startDate, Date endDate);

    List<PostEntity> findAllByAcceptedDateBetween(Date startDate, Date endDate);
}
