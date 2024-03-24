package com.demo.foodserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class LocationDto {
    private Integer locationId;
    private String doorNo;
    private String street;
    private String area;
    private String town;
    private String district;
    private String state;
    private String zipcode;
}
