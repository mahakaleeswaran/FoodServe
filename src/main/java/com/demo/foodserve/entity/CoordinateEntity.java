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
@Table(name="coordinates",schema = "public")
public class CoordinateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coordinateId;
    private Double latitude;
    private Double longitude;
    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;
}
