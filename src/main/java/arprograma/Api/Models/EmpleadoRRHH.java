package arprograma.Api.Models;

import java.util.ArrayList;
import java.util.List;

import arprograma.Api.Models.exeptions.TecnicoExistenteExeption;
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

    public Tecnico registrarTecnico(Empleado empleado) {
        tecnicosRegistrados.forEach(t -> {
            try {
                if (t.getEmpleado().getId_empleado() == empleado.getId_empleado()) {
                    throw new TecnicoExistenteExeption("El tecnico ya se encuentra registrado");
                }
            } catch (TecnicoExistenteExeption e) {
                e.printStackTrace();
            }
        });

        Tecnico tecnico = new Tecnico(empleado, this);
        this.tecnicosRegistrados.add(tecnico);

        return tecnico;
    }

    public boolean borrarTecnico(Tecnico tecnico) {
        return this.tecnicosRegistrados.remove(tecnico);
    }

    public boolean borrarTecnico(Long id_tecnico) {
        return this.tecnicosRegistrados.removeIf(t -> t.getId_tecnico() == id_tecnico);
    }

    public boolean modificarTecnico(Tecnico tecnico) {
        if (this.tecnicosRegistrados.contains(tecnico)) {
            this.tecnicosRegistrados.remove(tecnico);
            this.tecnicosRegistrados.add(tecnico);
            return true;
        } else {
            return false;
        }
    }

}
