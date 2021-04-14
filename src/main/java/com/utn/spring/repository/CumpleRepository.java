package com.utn.spring.repository;

import com.utn.spring.model.Cumpleañitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CumpleRepository extends JpaRepository<Cumpleañitos,Integer> {
}
