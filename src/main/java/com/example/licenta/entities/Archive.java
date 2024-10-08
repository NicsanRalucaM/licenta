package com.example.licenta.entities;

import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@NotNull(message = "text should not be null!")
    private Integer test;
    private Integer user;
    //@NotNull(message = "type should not be null!")
    private Integer component;
    //@NotNull(message = "test name should not be null!")
    private String name;
    private Boolean iscompleted;


}