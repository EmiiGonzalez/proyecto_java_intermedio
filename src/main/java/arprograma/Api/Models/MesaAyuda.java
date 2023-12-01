package arprograma.Api.Models;

import jakarta.persistence.EntityManager;

public class MesaAyuda {

    private EntityManager em;

    public MesaAyuda(EntityManager em) {
        this.em = em;
    }
    
    
}
