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
@Table(name="donor",schema = "public")
public class DonorEntity {
    @Id
    @Column(name = "donor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donor_Id;
    private Date registeredDate;
    private String name;
    private String organization;
    private String phoneNumber;
    private String email;
    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;
    @OneToMany
    @JoinColumn(name = "donor_id")
    private List<PostEntity> postEntity;


}

