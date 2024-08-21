package cl.javadevs.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.javadevs.springsecurityjwt.models.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    
}