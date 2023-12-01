package arprograma.Api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arprograma.Api.Models.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {


}
