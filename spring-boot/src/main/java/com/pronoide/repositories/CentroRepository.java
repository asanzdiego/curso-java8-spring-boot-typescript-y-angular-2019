package com.pronoide.repositories;

import com.pronoide.entities.Centro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository extends JpaRepository<Centro, Long> {

    Page<Centro> findAllByNombreLikeIgnoreCase(String localidad, Pageable pageable);
}