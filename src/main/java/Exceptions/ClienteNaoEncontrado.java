package Exceptions;

public class ClienteNaoEncontrado extends RuntimeException {
    public ClienteNaoEncontrado(int id) {
        super("Cliente com o id | " + id + " | nao foi encontrado!");
    }
}
