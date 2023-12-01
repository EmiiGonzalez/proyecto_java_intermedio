package arprograma.Api.Models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import arprograma.Api.Models.exeptions.ClienteExistenteExeption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "empleado_comercial")
@Data
public class EmpleadoComercial implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empleado_comercial")
    public Long id_empleado_comercial;

    @OneToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    public Empleado empleado;

    @OneToMany(mappedBy = "emCom", cascade = CascadeType.PERSIST)
    List<Cliente> clientesRegistrados = new ArrayList<>();

    public EmpleadoComercial() {
    }

    public EmpleadoComercial(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoComercial(Empleado empleado, List<Cliente> clientesRegistrados) {
        this.clientesRegistrados = clientesRegistrados;
        this.empleado = empleado;
    }

    public Cliente addCliente(Cliente cliente) {

        clientesRegistrados.forEach(c -> {
            try {
                if (c.getDni().equals(cliente.getDni()) || c.getRazonSocial().equals(cliente.getRazonSocial()) || c.getCuil().equals(cliente.getCuil())) {
                    throw new ClienteExistenteExeption("El cliente ya se encuentra registrado");
                }
            } catch (ClienteExistenteExeption e) {
                e.printStackTrace();
            }
        });

        Cliente clienteAux = new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getRazonSocial(), cliente.getCuil(), cliente.getEmail(), cliente.getTelefono(), this);
        this.clientesRegistrados.add(clienteAux);

        return clienteAux;
    }

    public Cliente addCliente (String nombre, String dni, String razonSocial, String cuil, String email, String telefono) {

        clientesRegistrados.forEach(c -> {
            try {
                if (c.getDni().equals(dni) || c.getRazonSocial().equals(razonSocial) || c.getCuil().equals(cuil)) {
                    throw new ClienteExistenteExeption("El cliente ya se encuentra registrado");
                }
            } catch (ClienteExistenteExeption e) {
                e.printStackTrace();
            }
        });

        Cliente clienteAux = new Cliente(nombre, dni, razonSocial, cuil, email, telefono, this);
        this.clientesRegistrados.add(clienteAux);

        return clienteAux;
    }

    @Override
    public String toString() {
        return "\nEmpleadoComercial: " + this.empleado.getNombre() + " \nId: " + this.id_empleado_comercial + "\nDNI: " + this.empleado.getDni() + "\nCantidad de clientes: " + this.clientesRegistrados.size();
    }

}
