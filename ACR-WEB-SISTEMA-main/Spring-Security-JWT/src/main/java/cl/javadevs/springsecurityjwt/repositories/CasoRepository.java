package cl.javadevs.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.javadevs.springsecurityjwt.models.Caso;

public interface CasoRepository extends JpaRepository<Caso, Integer> {
    
}
