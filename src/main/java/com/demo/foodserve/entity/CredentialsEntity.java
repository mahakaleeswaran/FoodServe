package com.demo.foodserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name="credentials",schema = "public")
public class CredentialsEntity {
    @Id
    private String userName;
    private String confirmPassword;
    private String userRole;
}
