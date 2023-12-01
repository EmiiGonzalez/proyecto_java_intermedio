package arprograma.Api.Services;

import java.util.Optional;

import java.util.List;
import org.springframework.stereotype.Service;

import arprograma.Api.Models.Empleado;
import arprograma.Api.Repository.EmpleadoRepository;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Optional<Empleado> buscarEmpleado(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void borrarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    public void modificarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    } 

    public void modificarEmpleado(String nombre, String dni, String email, String telefono, Long id) {
        Optional<Empleado> optionalEmpleado = buscarEmpleado(id);
        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            empleado.setNombre(nombre);
            empleado.setDni(dni);
            empleado.setEmail(email);
            empleado.setTelefono(telefono);
            modificarEmpleado(empleado);
        }
    }

    public void modificarEmpleado(Long id, Empleado empleado) {
        Empleado emp = empleadoRepository.findById(id).get();
        emp.setNombre(empleado.getNombre());
        emp.setDni(empleado.getDni());
        emp.setEmail(empleado.getEmail());
        emp.setTelefono(empleado.getTelefono());
        this.empleadoRepository.save(emp);
        
    }

    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarEmpleadoPorDni(String dni) {
        return empleadoRepository.findByDni(dni);
    }

}
