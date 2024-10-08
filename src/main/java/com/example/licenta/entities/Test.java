package com.example.licenta.entities;

import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user should not be null!")
    private Integer user;
    @NotNull(message = "name should not be null!")
    private String name;
    private String text;
    private Integer score;
    private Date date;


}