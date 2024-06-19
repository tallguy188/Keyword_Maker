package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Crawling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long crawlingId;

    @Column(length = 50000)
    private String rawData;

    @CreatedDate
    @Column
    private LocalDateTime createdTime;
}