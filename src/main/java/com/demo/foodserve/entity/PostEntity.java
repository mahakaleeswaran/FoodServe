package com.demo.foodserve.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
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
    private Date createdDate;
    private Date acceptedDate;
    private String phoneNumber;
    private String email;
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private DonorEntity donor;
    @ManyToOne
    @JoinColumn(name = "reciever_Id")
    private RecieverEntity receiver;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<FoodEntity> foodEntities;

}