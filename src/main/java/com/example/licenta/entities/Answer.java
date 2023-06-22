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

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@NotNull(message = "text should not be null!")
    private Integer result;
    private Integer archive;
    //@NotNull(message = "type should not be null!")
    private Integer question;
    //@NotNull(message = "test name should not be null!")
    private String value;
    private String text;
    private Boolean ismultiple;
    private Boolean ispair;
    private Boolean isbinar;
    private Boolean isfree;


}