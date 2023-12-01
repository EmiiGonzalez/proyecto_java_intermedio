package arprograma.Api.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ServicioDTO {
    private String nombre;
    private String tipo;
    private double precio;
    private LocalDate duracion;

    private Long cliente_id;
}
