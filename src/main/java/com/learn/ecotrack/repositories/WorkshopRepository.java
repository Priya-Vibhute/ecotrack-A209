package com.learn.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecotrack.entities.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop,Integer> {

}
