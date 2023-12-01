package arprograma.Api.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import arprograma.Api.DTO.ClienteDTO;
import arprograma.Api.DTO.ServicioDTO;
import arprograma.Api.Models.Cliente;
import arprograma.Api.Models.Empleado;
import arprograma.Api.Models.EmpleadoComercial;
import arprograma.Api.Models.Servicio;
import arprograma.Api.Repository.EmpleadoComercialRepository;

@Service
public class EmpleadoComercialService {
    private final EmpleadoComercialRepository empleadoComercialRepository;
    private final ClienteService clienteService;
    private final ServicioService servicioService;

    public EmpleadoComercialService(EmpleadoComercialRepository empleadoComercialRepository, ClienteService clienteService, EmpleadoService empleadoService, ServicioService servicioService) {
        this.empleadoComercialRepository = empleadoComercialRepository;
        this.clienteService = clienteService;
        this.servicioService = servicioService;
    }

    //******************************************
    //Empleado Comercial Methods
    //******************************************

    public void borrarEmpleadoComercial(Long id) {
        this.empleadoComercialRepository.deleteById(id);
    }

    public void modificarEmpleadoComercial(EmpleadoComercial empleadoComercial, Long id) {
        EmpleadoComercial old = this.empleadoComercialRepository.findById(id).get();
        old.setEmpleado(empleadoComercial.getEmpleado());
        old.setClientesRegistrados(empleadoComercial.getClientesRegistrados());
        this.empleadoComercialRepository.save(old);
    }

    public void crearEmpleadoComercial(Empleado empleado) {  
        EmpleadoComercial empleadoComercial = new EmpleadoComercial(empleado);
        this.empleadoComercialRepository.save(empleadoComercial);
    }

    public EmpleadoComercial buscarEmpleadoComercial(Long id) {
        return this.empleadoComercialRepository.findById(id).get();
    }

    public boolean existeEmpleadoComercial(Empleado empleado) {
        return this.empleadoComercialRepository.existsByEmpleado(empleado);
    }

    public boolean existeEmpleadoComercial(Long id) {
        return this.empleadoComercialRepository.existsById(id);
    }

    public List<EmpleadoComercial> obtenerEmpleadosComerciales() {
        return this.empleadoComercialRepository.findAll();
    }
    //*********************************************
    // Cliente Methods
    //*********************************************
    
    public void crearCliente(ClienteDTO cliente) {
        EmpleadoComercial empleadoComercial = this.empleadoComercialRepository.findById(cliente.getRegistradoPor()).get();
        Cliente newCliente = new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getCuil(), cliente.getRazonSocial(), cliente.getEmail(), cliente.getTelefono(), empleadoComercial);
        
        this.clienteService.agregar(newCliente);
    }

    public List<Cliente> obtenerClientesRegistrados(Long id) {
        EmpleadoComercial emCom = this.empleadoComercialRepository.findById(id).get();
        List <Cliente> clientes = this.clienteService.obtenerPorEmCom(emCom);
        return clientes;
    }

    public List<Cliente> obtenerClientes() {
        return this.clienteService.obtenerTodos();
    }

    public void borrarCliente(Long id) {
        this.clienteService.borrar(id);
    }
    
    public void borrarClienteDni(String dni) {
        Cliente cliente = this.clienteService.buscarDni(dni).get();
        this.clienteService.borrar(cliente);
        
    }

    public void borrarClienteCuil(String cuil) {
        Cliente cliente = this.clienteService.buscarCuil(cuil).get();
        this.clienteService.borrar(cliente);
    }

    public void borrarClienteRazonSocial(String razonSocial) {
        Cliente cliente = this.clienteService.buscarRazonSocial(razonSocial).get();
        this.clienteService.borrar(cliente);
    }

    public void modificarCliente(Cliente cliente, Long id) {
        Cliente old = this.clienteService.buscar(id).get();
        
        old.setRazonSocial(cliente.getRazonSocial());
        old.setCuil(cliente.getCuil());
        old.setDni(cliente.getDni());
        old.setEmail(cliente.getEmail());
        old.setTelefono(cliente.getTelefono());
        old.setServiciosContratados(cliente.getServiciosContratados());
        old.setEmCom(cliente.getEmCom());

        this.clienteService.modificar(old);
    }


    public boolean existeCliente(Long id) {
        return this.clienteService.clienteExistente(id);
    }
    public boolean existeCliente(Cliente cliente) {
        return this.clienteService.clienteExistente(cliente);
    }
    public boolean existeCliente(String datos) {
        return this.clienteService.clienteExistente(datos);
    }

    public Object buscarCliente(Long id) {
        return this.clienteService.buscar(id);
    }

    public Object buscarClienteDni(String dni) {
        return this.clienteService.buscarDni(dni);
    }

    public Object buscarClienteCuil(String cuil) {
        return this.clienteService.buscarCuil(cuil);
    }

    public Object buscarClienteRazonSocial(String razonSocial) {
        return this.clienteService.buscarRazonSocial(razonSocial);
    }

    //*********************************************
    // Servicio Methods
    //*********************************************

    public Object buscarServicio(Long id) {
        return this.servicioService.buscar(id);
    }

    public List<Servicio> buscarServicios() {
        return this.servicioService.buscarTodos();
    }

    public void borrarServicio(Long id) {
        this.servicioService.borrar(id);
    }

    public void borrarServicio(Servicio servicio) {
        this.servicioService.borrar(servicio);
    }

    public void modificarServicio(ServicioDTO servicio, Long id) {
        Servicio old = this.servicioService.buscar(id).get();
        
        old.setNombre(servicio.getNombre());
        old.setTipo(servicio.getTipo());
        old.setDuracion(servicio.getDuracion());
        old.setPrecio(servicio.getPrecio());

        this.servicioService.modificar(old);
    }

    public void crearServicio(ServicioDTO servicio) {
        Cliente cliente = this.clienteService.buscar(servicio.getCliente_id()).get();
        Servicio newServicio = new Servicio(servicio.getNombre(), servicio.getTipo(), servicio.getPrecio(),servicio.getDuracion(), cliente);

        this.servicioService.crear(newServicio);
    }

    public boolean existeServicio(Long id) {
        return this.servicioService.existeServicio(id);
    }
}
