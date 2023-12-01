package arprograma.Api.Models;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "soluciones")
public class Solucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_solucion;
    
    @Basic
    private String tipoProblema;
    private String descripcion;
    private String estadoProblema;
    @Column(name = "fecha_solucion")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaSolucion;

    @OneToOne
    @JoinColumn(name = "id_incidente")
    private Incidente incidente;

    @OneToOne
    @JoinColumn(name = "id_tecnico")
    private Tecnico tecnico;

    public Solucion(String tipoProblema, String descripcion, String estadoProblema, LocalDate fechaSolucion,
        Incidente incidente, Tecnico tecnico) {
        this.tipoProblema = tipoProblema;
        this.descripcion = descripcion;
        this.estadoProblema = estadoProblema;
        this.fechaSolucion = fechaSolucion;
        this.incidente = incidente;
        this.tecnico = tecnico;
    }

    public Solucion() {
        
    }

    public void resolverIncidente() {
        this.incidente.setEstado("Solucionado");
    }

    @Override
    public String toString() {
        return "Solucion [id_solucion=" + id_solucion + ", tipoProblema=" + tipoProblema + ", descripcion="
            + descripcion + ", estadoProblema=" + estadoProblema + ", fechaSolucion=" + fechaSolucion
            + ", incidente=" + incidente.getDescripcion() + ", tecnico=" + tecnico.getEmpleado().getNombre() + "]";
    }
}
