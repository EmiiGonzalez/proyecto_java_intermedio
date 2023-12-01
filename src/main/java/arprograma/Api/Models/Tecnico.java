package arprograma.Api.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tecnico;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.PERSIST)
    private List<Especialidad> especialidades = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "registrado_por_RRHH", referencedColumnName = "id_empleado_rrhh")
    private EmpleadoRRHH emRRHH;

    public Tecnico(Empleado empleado, EmpleadoRRHH emRRHH) {
        this.empleado = empleado;
        this.emRRHH = emRRHH;
    }

    public Tecnico(Empleado empleado, List<Especialidad> especialidades, EmpleadoRRHH emRRHH) {
        this.especialidades = especialidades;
        this.empleado = empleado;
        this.emRRHH = emRRHH;
    }

    public Tecnico() {
    }

    public void addEspecialidad(Especialidad especialidad) {
        Especialidad especialidadNew = new Especialidad(especialidad.getNombre(), this);
        this.getEspecialidades().add(especialidadNew);
        System.out.println("Se agrego la especialidad: " + especialidad.getNombre());
    }

    public ArrayList<String> getListaEspecialidades() {
        List<String> especialidades = this.getEspecialidades().stream().map(Especialidad::getNombre).toList();
        return new ArrayList<>(especialidades);
    }

    public Solucion generarSolucion(Incidente incidente, String tipoProblema, String descripcion, String estadoProblema, LocalDate fechaSolucion) {
        Solucion solucion = new Solucion(tipoProblema, descripcion, estadoProblema, fechaSolucion, incidente, this);
        return solucion;
    }

    @Override
    public String toString() {
        return "Tecnico: " + this.getEmpleado().getNombre()+ " \nId: " + this.getId_tecnico() + "\nDNI: " + this.getEmpleado().getDni() + "\nEspecialidades: " + this.getListaEspecialidades();
    }
}
