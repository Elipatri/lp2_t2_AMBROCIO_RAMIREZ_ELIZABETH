package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository;

import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
