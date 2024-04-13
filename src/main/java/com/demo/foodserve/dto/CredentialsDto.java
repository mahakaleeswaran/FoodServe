package com.demo.foodserve.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CredentialsDto {
    private String username;
    private String confirmPassword;
    private String userRole;
}
