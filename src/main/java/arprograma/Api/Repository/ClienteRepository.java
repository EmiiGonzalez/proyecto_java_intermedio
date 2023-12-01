package arprograma.Api.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arprograma.Api.Models.Cliente;
import arprograma.Api.Models.EmpleadoComercial;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    public Optional<Cliente> findByDni(String dni);
    public Optional<Cliente> findByCuil(String cuil);
    public Optional<Cliente> findByRazonSocial(String razonSocial);

    public List<Cliente> findByEmCom(EmpleadoComercial emCom);
    
}
