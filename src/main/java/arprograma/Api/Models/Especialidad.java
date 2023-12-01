package arprograma.Api.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "especialidades")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_especialidad;
    private String nombre;

    // Relaciones entre tablas n a 1
    @ManyToOne
    @JoinColumn(name = "tecnico_asignado", referencedColumnName = "id_tecnico")
    private Tecnico tecnico;

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }
    public Especialidad(String nombre, Tecnico tecnico) {
        this.nombre = nombre;
        this.tecnico = tecnico;
    }

    public Especialidad() {
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

}