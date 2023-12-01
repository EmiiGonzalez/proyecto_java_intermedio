package arprograma.Api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arprograma.Api.Models.Empleado;
import arprograma.Api.Models.EmpleadoRRHH;

@Repository
public interface EmpleadoRRHHRepository extends JpaRepository<EmpleadoRRHH, Long> {
    
    public EmpleadoRRHH findByEmpleado(Empleado empleado);

}
