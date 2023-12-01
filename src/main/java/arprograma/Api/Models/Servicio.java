package arprograma.Api.Models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_servicio;
    @Column
    private String nombre;
    @Column
    private String tipo;
    @Column
    private double precio;
    @Temporal(TemporalType.DATE)
    private LocalDate duracion;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @JsonIgnore
    private Cliente cliente;

    public Servicio() {
    }

    public Servicio(String nombre, String tipo, double precio, LocalDate duracion, Cliente cliente) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.duracion = duracion;
        this.cliente = cliente;
    }

    public Servicio(String nombre, String tipo, double precio, LocalDate duracion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.duracion = duracion;
    }

    @JsonProperty("id_cliente")
    public Long getId_servicio() {
        return id_servicio;
    }

    @Override
    public String toString() {
        return "Servicio: " + this.getNombre() + " \nId: " + this.getId_servicio() + "\nTipo: " + this.getTipo() + "\nDuracion: " + this.getDuracion() + "\nCliente: " + this.getCliente().getNombre();
    }
}
