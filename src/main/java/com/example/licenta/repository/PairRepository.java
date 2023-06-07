package com.example.licenta.repository;

import com.example.licenta.entities.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairRepository extends JpaRepository<Pair, Integer> {}
