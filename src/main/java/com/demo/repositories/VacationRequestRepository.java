package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.VacationRequest;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

}
