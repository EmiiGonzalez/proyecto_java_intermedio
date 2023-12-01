package arprograma.Api.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arprograma.Api.Models.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    public Optional<Empleado> findByDni(String dni);
    
}
