package arprograma.Api.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import arprograma.Api.DTO.EmpleadoDTO;
import arprograma.Api.Models.Empleado;
import arprograma.Api.Services.EmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @PostMapping(value = "/registrar", headers = "Accept=application/json")
    public ResponseEntity<?> registrarEmpleado(@RequestBody EmpleadoDTO datos) {
        Empleado em = new Empleado();
        em.setNombre(datos.getNombre());
        em.setDni(datos.getDni());
        em.setEmail(datos.getEmail());
        em.setTelefono(datos.getTelefono());

        this.service.guardarEmpleado(em);

        return ResponseEntity.status(HttpStatus.CREATED).body(em);
    }

    @GetMapping(value = "/obtener", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarTodos() {
        List<Empleado> empleados = this.service.obtenerEmpleados();
        if (empleados.isEmpty()) {
            String mensaje = "No se encontró empleados";
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensaje);
        } else {
            return ResponseEntity.ok(empleados);
        }
    }

    @GetMapping(value = "/obtener/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarUno(@PathVariable Long id) {
        Optional<Empleado> empleadoOptional = this.service.buscarEmpleado(id);

        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            return ResponseEntity.ok(empleado);
        } else {
            String mensaje = "No se encontró ningún empleado con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @DeleteMapping(value = "/borrar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (this.service.buscarEmpleado(id).isPresent()) {
            this.service.borrarEmpleado(id);
            String mensaje = "Se borro el empleado con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el empleado con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @PutMapping(value = "/editar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody EmpleadoDTO datos) {
        if (this.service.buscarEmpleado(id).isPresent()) {
            Empleado empleado = new Empleado(datos.getNombre(), datos.getDni(), datos.getEmail(), datos.getTelefono());
            this.service.modificarEmpleado(id, empleado);
            String mensaje = "Se modificó el empleado con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el empleado con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @GetMapping(value = "/buscarDni/{dni}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> buscarPorDni(@PathVariable String dni) {
        if (this.service.buscarEmpleadoPorDni(dni).isPresent()) {
            Empleado empleado = this.service.buscarEmpleadoPorDni(dni).get();
            return ResponseEntity.ok(empleado);
        } else {
            String mensaje = "No se encontró el empleado con el DNI: " + dni;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}
