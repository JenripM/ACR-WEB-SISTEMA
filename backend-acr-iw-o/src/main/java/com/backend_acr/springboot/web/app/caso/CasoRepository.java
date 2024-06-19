package com.backend_acr.springboot.web.app.caso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasoRepository extends JpaRepository<Caso, Integer> {
    
}
