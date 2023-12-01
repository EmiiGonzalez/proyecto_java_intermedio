package arprograma.Api.Controllers;

import java.util.List;
import java.util.Optional;

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

import arprograma.Api.DTO.ClienteDTO;
import arprograma.Api.DTO.EmpleadoComercialDTO;
import arprograma.Api.DTO.ServicioDTO;
import arprograma.Api.Models.Cliente;
import arprograma.Api.Models.Empleado;
import arprograma.Api.Models.EmpleadoComercial;
import arprograma.Api.Models.Servicio;
import arprograma.Api.Services.EmpleadoComercialService;
import arprograma.Api.Services.EmpleadoService;

@RestController
@RequestMapping("/comercial")
public class EmpleadoComercialController {
    private EmpleadoComercialService service;
    private EmpleadoService empleadoService;

    public EmpleadoComercialController(EmpleadoComercialService service, EmpleadoService empleadoService) {
        this.service = service;
        this.empleadoService = empleadoService;
    }

    // ******************************
    // Empleados comerciales Routes
    // ******************************

    @PostMapping(value = "/registrar", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> registrarEmpleado(@RequestBody EmpleadoComercialDTO datos) {
        if (empleadoService.buscarEmpleado(datos.getId_empleado()).isPresent()) {
            Empleado empleado = empleadoService.buscarEmpleado(datos.getId_empleado()).get();
            if (service.existeEmpleadoComercial(empleado)) {
                String mensaje = "El empleado ya se encuentra registrado";
                return ResponseEntity.status(409).body(mensaje);
            } else {
                service.crearEmpleadoComercial(empleado);
                return ResponseEntity.status(201).body("Se registro el comercial con el ID: " + datos.getId_empleado());
            }
        } else {
            String mensaje = "No se encontró el empleado con el ID: " + datos.getId_empleado();
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping(value = "/obtener", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarTodos() {
        List<EmpleadoComercial> empleados = service.obtenerEmpleadosComerciales();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping(value = "/obtener/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarUno(@PathVariable Long id) {
        if (service.existeEmpleadoComercial(id)) {
            EmpleadoComercial empleado = service.buscarEmpleadoComercial(id);
            return ResponseEntity.ok(empleado);
        } else {
            String mensaje = "No se encontró el comercial con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @DeleteMapping(value = "/borrar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (service.existeEmpleadoComercial(id)) {
            service.borrarEmpleadoComercial(id);
            String mensaje = "Se borro el comercial con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el comercial con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @PutMapping(value = "/editar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody EmpleadoComercialDTO datos) {
        if (service.existeEmpleadoComercial(id)) {
            Empleado empleado = empleadoService.buscarEmpleado(datos.getId_empleado()).get();

            service.modificarEmpleadoComercial(empleado, id);
            String mensaje = "Se modificó el comercial con el ID: " + id;

            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el comercial con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    // ******************************
    // Clientes Routes
    // ******************************

    @PostMapping(value = "/cliente/registrar", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> registrarCliente(@RequestBody ClienteDTO datos) {
        if (service.existeCliente(datos.getDni()) || service.existeCliente(datos.getCuil())
                || service.existeCliente(datos.getRazonSocial())) {
            String mensaje = "El cliente ya se encuentra registrado";
            return ResponseEntity.status(409).body(mensaje);
        } else {
            service.crearCliente(datos);
            ;
            return ResponseEntity.status(201)
                    .body("Se registro el cliente: " + datos.getNombre() + " con el DNI: " + datos.getDni());
        }
    }

    @GetMapping(value = "/cliente/obtener", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarTodosClientes() {
        List<Cliente> clientes = service.obtenerClientes();
        if (clientes.isEmpty()) {
            String mensaje = "No se encontró clientes";
            return ResponseEntity.status(404).body(mensaje);
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @GetMapping(value = "/cliente/obtener/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> buscarUnCliente(@PathVariable Long id) {
        if (service.existeCliente(id)) {
            return ResponseEntity.ok(service.buscarCliente(id));
        } else {
            String mensaje = "No se encontró el cliente con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping(value = "/cliente/obtenerDni/{dni}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> buscarUnClienteDni(@PathVariable String dni) {
        if (service.existeCliente(dni)) {
            return ResponseEntity.ok(service.buscarClienteDni(dni));
        } else {
            String mensaje = "No se encontró el cliente con el ID: " + dni;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping(value = "/cliente/obtenerCuil/{cuil}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> buscarUnClienteCuil(@PathVariable String cuil) {
        if (service.existeCliente(cuil)) {
            return ResponseEntity.ok(service.buscarClienteCuil(cuil));
        } else {
            String mensaje = "No se encontró el cliente con el ID: " + cuil;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping(value = "/cliente/obtenerRazonSocial/{razonSocial}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> buscarUnClienteRazonSocial(@PathVariable String razonSocial) {
        if (service.existeCliente(razonSocial)) {
            return ResponseEntity.ok(service.buscarClienteRazonSocial(razonSocial));
        } else {
            String mensaje = "No se encontró el cliente con el ID: " + razonSocial;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @DeleteMapping(value = "/cliente/borrar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
        if (service.existeCliente(id)) {
            service.borrarCliente(id);
            String mensaje = "Se borro el cliente con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el cliente con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @DeleteMapping(value = "cliente/borrarDni/{dni}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> borrarDni(@PathVariable String dni) {
        if (service.existeCliente(dni)) {
            service.borrarClienteDni(dni);
            String mensaje = "Se borro el cliente con el DNI: " + dni;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el cliente con el DNI: " + dni;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @DeleteMapping(value = "cliente/borrarCuil/{cuil}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> borrarCuil(@PathVariable String cuil) {
        if (service.existeCliente(cuil)) {
            service.borrarClienteCuil(cuil);
            String mensaje = "Se borro el cliente con el CUIL: " + cuil;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el cliente con el CUIL: " + cuil;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @PutMapping(value = "cliente/editar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody ClienteDTO datos) {
        if (service.existeCliente(id)) {
            service.modificarCliente(datos, id);
            String mensaje = "Se modificó el cliente con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el cliente con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    // ******************************
    // Servicios Routes
    // ******************************

    @GetMapping(value = "servicio/obtener/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarUnServicio(@PathVariable Long id) {
        if (service.existeServicio(id)) {
            return ResponseEntity.ok(service.buscarServicio(id));
        } else {
            String mensaje = "No se encontró el servicio con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @GetMapping(value = "servicio/obtener", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> mostrarTodosServicios() {
        List<Servicio> servicios = service.buscarServicios();
        if (servicios.isEmpty()) {
            String mensaje = "No se encontró servicios";
            return ResponseEntity.status(404).body(mensaje);
        } else {
            return ResponseEntity.ok(servicios);
        }
    }

    @DeleteMapping(value = "servicio/borrar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> borrarServicio(@PathVariable Long id) {
        if (service.existeServicio(id)) {
            service.borrarServicio(id);
            String mensaje = "Se borro el servicio con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el servicio con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @PostMapping(value = "servicio/registrar", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> registrarServicio(@RequestBody ServicioDTO datos) {
        if (service.existeCliente(datos.getCliente_id())) {
            service.crearServicio(datos);
            return ResponseEntity.status(201).body("Se registro el servicio: " + datos.getNombre());
        } else {
            String mensaje = "No se encontró el cliente con el ID: " + datos.getCliente_id();
            return ResponseEntity.status(404).body(mensaje);
        }
    }

    @PutMapping(value = "servicio/editar/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> modificarServicio(@PathVariable Long id, @RequestBody ServicioDTO datos) {
        if (service.existeServicio(id)) {
            service.modificarServicio(datos, id);
            String mensaje = "Se modificó el servicio con el ID: " + id;
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el servicio con el ID: " + id;
            return ResponseEntity.status(404).body(mensaje);
        }
    }
}
