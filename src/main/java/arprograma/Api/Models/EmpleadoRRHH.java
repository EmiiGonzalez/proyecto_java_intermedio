package arprograma.Api.Models;

import java.util.ArrayList;
import java.util.List;

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

@Data
@Entity
@Table(name = "empleado_rrhh")
public class EmpleadoRRHH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado_rrhh")
    private Long id_empleado_rrhh;

    @OneToMany(mappedBy = "emRRHH", cascade = CascadeType.PERSIST)
    private List<Tecnico> tecnicosRegistrados = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private Empleado empleado;

    public EmpleadoRRHH() {
    }

    public EmpleadoRRHH(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoRRHH(Empleado empleado, List<Tecnico> tecnicosRegistrados) {
        this.tecnicosRegistrados = tecnicosRegistrados;
        this.empleado = empleado;
    }
}
