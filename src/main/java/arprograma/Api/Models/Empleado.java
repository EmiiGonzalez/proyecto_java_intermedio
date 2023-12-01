package arprograma.Api.Models;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "empleados")
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_empleado;
    @Basic
    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    public Empleado(String nombre,String dni , String telefono, String email ) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
    }

    public Empleado() {
    }

    @Override
    public String toString() {
        return "Empleado: " + this.getNombre()+ " \nId: " + this.getId_empleado() + "\nDNI: " + this.getDni() + "\nEmail: " + this.getEmail() + "\nTeléfono: " + this.getTelefono();
    }

}
