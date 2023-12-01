package arprograma.Api.Services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import arprograma.Api.Models.Cliente;
import arprograma.Api.Models.EmpleadoComercial;
import arprograma.Api.Repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> buscarRazonSocial(String razonSocial) {
        return clienteRepository.findByRazonSocial(razonSocial);
    }

    public Optional<Cliente> buscarCuil(String cuil) {
        return clienteRepository.findByCuil(cuil);
    }

    public Optional<Cliente> buscarDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    public void borrar(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public Optional<Cliente> buscar(Long id) {
        return clienteRepository.findById(id);
    }

    public void borrar(Long id) {
        clienteRepository.deleteById(id);
    }

    public void modificar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void agregar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public List<Cliente> obtenerPorEmCom(EmpleadoComercial emCom) {
        return clienteRepository.findByEmCom(emCom);
    }

    public boolean clienteExistente(String datos) {
        boolean existePorDni = this.buscarDni(datos).isPresent();
        boolean existePorCuil = this.buscarCuil(datos).isPresent();
        boolean existePorRazonSocial = this.buscarRazonSocial(datos).isPresent();

        return existePorDni || existePorCuil || existePorRazonSocial;
    }

    public boolean clienteExistente(Cliente cliente) {
        return this.clienteExistente(cliente.getDni()) || this.clienteExistente(cliente.getCuil()) || this.clienteExistente(cliente.getRazonSocial());
    }

    public boolean clienteExistente(Long id) {
        return this.buscar(id).isPresent();
    }

}
