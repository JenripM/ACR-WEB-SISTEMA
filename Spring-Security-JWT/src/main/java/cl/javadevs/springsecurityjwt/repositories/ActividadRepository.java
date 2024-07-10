package cl.javadevs.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.javadevs.springsecurityjwt.models.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

}
