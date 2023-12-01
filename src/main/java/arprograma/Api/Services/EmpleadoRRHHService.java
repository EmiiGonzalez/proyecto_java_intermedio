package arprograma.Api.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import arprograma.Api.DTO.EmpleadoRRHHDTO;
import arprograma.Api.Models.Empleado;
import arprograma.Api.Models.EmpleadoRRHH;
import arprograma.Api.Repository.EmpleadoRRHHRepository;

@Service
public class EmpleadoRRHHService {

    private final EmpleadoRRHHRepository empleadoRRHHRepository;

    public EmpleadoRRHHService(EmpleadoRRHHRepository empleadoRRHHRepository) {
        this.empleadoRRHHRepository = empleadoRRHHRepository;
    }

    public EmpleadoRRHH findByEmpleado(Empleado empleado) {
        return this.empleadoRRHHRepository.findByEmpleado(empleado);
    }

    public void crear(Empleado empleado) {
        EmpleadoRRHH empleadoRRHH = new EmpleadoRRHH(empleado);
        this.empleadoRRHHRepository.save(empleadoRRHH);
    }

    public boolean existe(Long id) {
        return this.empleadoRRHHRepository.existsById(id);
    }

    public Optional<EmpleadoRRHH> buscar(Long id) {
        return this.empleadoRRHHRepository.findById(id);
    }

    public List<EmpleadoRRHH> obtenerEmpleadosRRHH() {
        return this.empleadoRRHHRepository.findAll();
    }

    public void borrar(Long id) {
        this.empleadoRRHHRepository.deleteById(id);
    }

    public void modificar(Long id, Empleado em) {
        EmpleadoRRHH old = this.buscar(id).get();
        old.setEmpleado(em);
        this.empleadoRRHHRepository.save(old);
    }


    
}
