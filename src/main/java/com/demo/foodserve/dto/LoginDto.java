package com.demo.foodserve.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class LoginDto {
    private Integer userId;
    private String userRole;
    private String username;
}
