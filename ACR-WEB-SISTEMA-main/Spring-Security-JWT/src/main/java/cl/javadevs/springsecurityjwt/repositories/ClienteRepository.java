package cl.javadevs.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.javadevs.springsecurityjwt.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
