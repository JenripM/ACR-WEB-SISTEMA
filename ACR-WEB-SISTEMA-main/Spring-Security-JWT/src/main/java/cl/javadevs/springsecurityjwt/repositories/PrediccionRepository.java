package cl.javadevs.springsecurityjwt.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.javadevs.springsecurityjwt.models.Prediccion;

public interface PrediccionRepository extends JpaRepository<Prediccion, Integer> {
    List<Prediccion> findByIdCliente(int clienteId);
}
