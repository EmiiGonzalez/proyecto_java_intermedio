package arprograma.Api.Models;

import java.time.LocalDate;

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

@Entity
@Table(name = "incidentes")
@Data
public class Incidente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_incidente;

  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "id_servicio")
  private Servicio servicio;

  @Column(name = "tipo_problema")
  private String tipoProblema;

  @Column(name = "descripcion")
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "tecnico_asignado", referencedColumnName = "id_tecnico")
  private Tecnico tecnicoAsignado;

  @Column(name = "fecha_registro")
  @Temporal(TemporalType.DATE)
  private LocalDate fechaRegistro;

  @Column(name = "fecha_estimada_solucion")
  @Temporal(TemporalType.DATE)
  private LocalDate fechaEstimadaSolucion;

  @Column(name = "is_complejo")
  private boolean isComplejo;

  @Column(name = "estado")
  private String estado;

  public Incidente(Cliente cliente, Servicio servicio, String tipoProblema, String descripcion,
    String fechaEstimadaSolucion, boolean isComplejo, String estado) {
    this.cliente = cliente;
    this.servicio = servicio;
    this.tipoProblema = tipoProblema;
    this.descripcion = descripcion;
    this.fechaRegistro = LocalDate.now();
    this.fechaEstimadaSolucion = LocalDate.parse(fechaEstimadaSolucion);
    this.isComplejo = isComplejo;
    this.estado = estado;
  }

}