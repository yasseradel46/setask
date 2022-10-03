package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.VacationType;

@Repository
public interface VacationTypeRepository extends JpaRepository<VacationType, Long> {

}
