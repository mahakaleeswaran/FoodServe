package com.demo.foodserve.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class DateDto {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date endDate;
}
