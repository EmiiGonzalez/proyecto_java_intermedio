package arprograma.Api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arprograma.Api.Models.Empleado;
import arprograma.Api.Models.EmpleadoComercial;

@Repository
public interface EmpleadoComercialRepository extends JpaRepository<EmpleadoComercial, Long> {
    public boolean existsByEmpleado(Empleado empleado);
}
