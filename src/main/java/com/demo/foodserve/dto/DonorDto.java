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
public class DonorDto {
    private String name;
    private String organization;
    private List<PostDto> posts;
    private String phoneNumber;
    private String email;
    private String address;
}
