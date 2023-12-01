package arprograma.Api.Controllers;

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

import arprograma.Api.DTO.EmpleadoRRHHDTO;
import arprograma.Api.Models.Empleado;
import arprograma.Api.Models.EmpleadoRRHH;
import arprograma.Api.Services.EmpleadoRRHHService;
import arprograma.Api.Services.EmpleadoService;

@RestController
@RequestMapping("/rrhh")
public class EmpleadoRRHHController {

    private final EmpleadoRRHHService service;
    private final EmpleadoService empleadoService;

    public EmpleadoRRHHController(EmpleadoRRHHService service, EmpleadoService empleadoService) {
        this.service = service;
        this.empleadoService = empleadoService;
    }

    //***********************
    //EmpleadoRRHH Routes
    //***********************

    @PostMapping(value = "/registrar", headers = "Accept=application/json")
    @ResponseBody 
    public ResponseEntity<?> registrar(@RequestBody EmpleadoRRHHDTO empleado) {
        if (empleadoService.buscarEmpleado(empleado.getId_empleado()).isPresent()) {
            Empleado em = empleadoService.buscarEmpleado(empleado.getId_empleado()).get();
            service.crear(em);
            return ResponseEntity.status(HttpStatus.CREATED).body(em);
        } else {
            String mensaje = "No se encontró el empleado con el ID: " + empleado.getId_empleado();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @GetMapping(value = "/obtener/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarUno(@PathVariable Long id) {
        if (service.existe(id)) {
            EmpleadoRRHH em = service.buscar(id).get();
            return ResponseEntity.ok(em);
        } else {
            String mensaje = "No se encontró el RRHH con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @GetMapping(value = "/obtener", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarTodos() {
        return ResponseEntity.ok(service.obtenerEmpleadosRRHH());
    }

    @DeleteMapping(value = "/borrar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (service.existe(id)) {
            service.borrar(id);
            String mensaje = "Se borro el RRHH con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el RRHH con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @PutMapping(value = "/editar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody EmpleadoRRHHDTO datos) {
        if (service.existe(id)) {
            Empleado em = empleadoService.buscarEmpleado(datos.getId_empleado()).get();
            service.modificar(id, em);
            String mensaje = "Se modificó el RRHH con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el RRHH con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        
    }
    
}
