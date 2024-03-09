package com.demo.foodserve.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name="post",schema = "public")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;
    private Boolean served;
    private String phoneNumber;
    private String email;
    private Integer donor_Id;
    private Integer reciever_id;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<FoodEntity> foodEntities;

}