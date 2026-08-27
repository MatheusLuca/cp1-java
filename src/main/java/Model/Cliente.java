package Model;

public class Cliente {

    private String nome;
    private String telefone;
    private Integer id;



    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;

    }

    public Cliente(String nome, String telefone, Integer id) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", id=" + id +
                '}';
    }
}
