package com.urlshortener.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urlshortener.apirest.models.Pair;

public interface PairRepository extends JpaRepository<Pair, Long>{

}
