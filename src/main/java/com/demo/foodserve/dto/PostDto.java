package com.demo.foodserve.dto;

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
public class PostDto {
    private List<FoodDto> posts;
    private String location;
    private Boolean served;
}
