package cl.javadevs.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.javadevs.springsecurityjwt.models.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
}
