package arprograma.Api.Services;

import java.util.Optional;

import java.util.List;
import org.springframework.stereotype.Service;

import arprograma.Api.Models.Servicio;
import arprograma.Api.Repository.ServicioRepository;

@Service
public class ServicioService {
    
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }


    public Optional<Servicio> buscar(Long id) {
        return servicioRepository.findById(id);
    }

    public Optional<Servicio> buscar(Servicio servicio) {
        return servicioRepository.findById(servicio.getId_servicio());
    }

    public Servicio crear(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public void borrar(Long id) {
        servicioRepository.deleteById(id);
    }

    public void borrar(Servicio servicio) {
        servicioRepository.deleteById(servicio.getId_servicio());
    }

    public void modificar(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    public List<Servicio> buscarTodos() {
        return servicioRepository.findAll();
    }


    public boolean existeServicio(Long id) {
        return this.buscar(id).isPresent();
    }
}
