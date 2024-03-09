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
@Table(name="reciever",schema = "public")
public class RecieverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reciever_id;
    private String name;
    private String organization;
    private Date registeredDate;
    private String phoneNumber;
    private String email;
    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;
    @OneToMany
    @JoinColumn(name = "reciever_id")
    private List<PostEntity> postEntity;
}
