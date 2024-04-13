package com.demo.foodserve.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name="feedback",schema = "public")
public class FeedBackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String feedBack;
}
