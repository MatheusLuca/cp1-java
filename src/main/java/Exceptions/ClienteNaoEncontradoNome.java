package Exceptions;

public class ClienteNaoEncontradoNome extends RuntimeException {
    public ClienteNaoEncontradoNome(String nome) {
        super("Cliente com o nome | " + nome + " | nao foi encontrado!");
    }
}
