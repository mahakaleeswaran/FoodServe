package com.demo.foodserve.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CountDto {
    private Integer donorsCount;
    private Integer recieversCount;
    private Double foodCount;
}
