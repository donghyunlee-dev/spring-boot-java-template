package com.windsoft.starter.auth.data.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    private Long id;
    private String email;
    private String name;
}
