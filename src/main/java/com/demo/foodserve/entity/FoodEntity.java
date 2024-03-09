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
@Table(name="food_items",schema = "public")
public class FoodEntity {

    private Integer post_Id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer food_Id;
    private String foodName;
    private Double quantity;
}
