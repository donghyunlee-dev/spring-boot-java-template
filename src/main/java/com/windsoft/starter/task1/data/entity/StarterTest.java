package com.windsoft.starter.task1.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "starter_test")
@Entity
public class StarterTest {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
