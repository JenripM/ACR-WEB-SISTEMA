package cl.javadevs.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.javadevs.springsecurityjwt.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
