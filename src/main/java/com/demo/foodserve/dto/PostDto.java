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
    private Integer id;
    private String donorName;
    private String donorPhoneNumber;
    private String donorEmail;
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverEmail;
    private List<FoodDto> posts;
    private Date createdDate;
    private String location;
    private Boolean served;
    private Double latitude;
    private Double longitude;
}
