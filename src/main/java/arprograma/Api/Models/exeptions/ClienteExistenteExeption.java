package arprograma.Api.Models.exeptions;

public class ClienteExistenteExeption extends Exception {
    public ClienteExistenteExeption(String message) {
        super(message);
    }
}
