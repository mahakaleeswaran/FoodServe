package com.demo.foodserve.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name="Location",schema = "public")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer location_id;
    private String doorNo;
    private String street;
    private String area;
    private String town;
    private String district;
    private String state;
    private String zipcode;
}