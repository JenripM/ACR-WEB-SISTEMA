package cl.javadevs.springsecurityjwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.javadevs.springsecurityjwt.models.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByCasoId(int casoId);
}
