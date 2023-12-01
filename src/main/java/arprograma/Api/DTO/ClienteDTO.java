package arprograma.Api.DTO;

import lombok.Data;

@Data
public class ClienteDTO {

    private String nombre;
    private String dni;
    private String razonSocial;
    private String cuil;
    private String email;
    private String telefono;
    
    private Long registradoPor;
}
