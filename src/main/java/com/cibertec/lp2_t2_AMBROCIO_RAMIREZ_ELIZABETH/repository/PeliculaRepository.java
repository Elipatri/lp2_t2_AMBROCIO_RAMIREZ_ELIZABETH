package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}
