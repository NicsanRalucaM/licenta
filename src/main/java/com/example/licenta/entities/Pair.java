package com.example.licenta.entities;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@NotNull(message = "text should not be null!")
    private Integer question;
    private Integer numberCorrect;
    //@NotNull(message = "type should not be null!")
    private String clue;
    private String letterClue;
    //@NotNull(message = "test name should not be null!")
    private String correct;


}