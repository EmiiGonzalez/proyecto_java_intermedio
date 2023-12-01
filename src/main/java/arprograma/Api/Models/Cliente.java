package arprograma.Api.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cliente;
    @Column
    private String nombre;
    @Column
    private String dni;
    @Column
    private String cuil;
    @Column
    private String razonSocial;
    @Column
    private String email;
    @Column
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Servicio> serviciosContratados = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "registrado_por", referencedColumnName = "id_empleado_comercial")
    @JsonIgnore
    private EmpleadoComercial emCom;

    public Cliente() {
        
    }

    public Cliente(String nombre, String dni, String cuil, String razonSocial, String email, String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.cuil = cuil;
        this.razonSocial = razonSocial;
        this.email = email;
        this.telefono = telefono;
    }

    public Cliente(String nombre, String dni, String cuil, String razonSocial, String email, String telefono, EmpleadoComercial emCom) {
        this.nombre = nombre;
        this.dni = dni;
        this.cuil = cuil;
        this.razonSocial = razonSocial;
        this.email = email;
        this.telefono = telefono;
        this.emCom = emCom;
    }

    public void addServicioContratado(Servicio servicio) {
        Servicio servicioAux = new Servicio(servicio.getNombre(), servicio.getTipo(), servicio.getPrecio(), servicio.getDuracion(), this); 
        this.serviciosContratados.add(servicioAux);
    }

    @JsonProperty("registrado_por_empleado_comercial")
    public Long getRegistradoPorId() {
        return (emCom != null) ? emCom.getId_empleado_comercial() : null;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.getNombre() + " \nId: " + this.getId_cliente() + "\nDNI: " + this.getDni() + "\nCUIT: " + this.getCuil() + "\nEmail: " + this.getEmail() + "\nTeléfono: " + this.getTelefono() + "\nServicios Contratados: " + this.getServiciosContratados().size();
    }
}

