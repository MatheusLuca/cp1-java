package DAO;

import Conexao.Conexao;
import Exceptions.ClienteNaoEncontrado;
import Exceptions.ClienteNaoEncontradoNome;
import Model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDao {

    public static void inserir(Cliente cliente) {

        //Scripr sql para efetuar o insert do cliente
        String sql = """
                INSERT INTO TB_CLIENTES (id_cliente, nome, telefone)
                    VALUES(SEQ_TB_CLIENTE.NEXTVAL, ?, ?)
                """;

        try {
            // Enviar comando sql ao banco
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.executeUpdate();
            System.out.println("Salvo no banco!!!!");
            Conexao.fecharConexao();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String recuperarClientes() {
        String sqlQuery = """
                SELECT * FROM TB_CLIENTES
                """;

        try {

            PreparedStatement stmt = Conexao.conectar().prepareStatement(sqlQuery);
            ResultSet resultadoConsulta = stmt.executeQuery();
            System.out.println("\n========================================");
            System.out.println(" LISTA DE CLIENTES");
            System.out.println("========================================");
            // next() move o cursor para o proximo registros
            //Retorna true se existir um proximo registros
            //Retorna false caso nao exista mais registros

            while (resultadoConsulta.next()) {
                int id = resultadoConsulta.getInt("id_cliente");
                String nomeCliente = resultadoConsulta.getString("nome");
                String telefoneCliente = resultadoConsulta.getString("telefone");

                System.out.println("----------------------------------------");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nomeCliente);
                System.out.println("Telefone: " + telefoneCliente);
                System.out.println("----------------------------------------");
            }
            Conexao.fecharConexao();

        } catch (SQLException e) {
            System.out.println("Erro na consulta!" + e.getErrorCode());
            ;
        }
        return null;
    }


    public static Cliente recuperarClientePorId(int id) {
        //  Query SQL
        String sqlRetornoID = """
                SELECT id_cliente, nome, telefone
                    FROM TB_CLIENTES
                        WHERE id_cliente = ?
                """;
        try {
            // recurso para preparar e executar comandos sql com parametros
            // Conecte com o banco e prepare o comando sql com parametro e armazena em stmt
            // stmt -> manda o sql para o banco
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sqlRetornoID);

            //Métodos stmt servem para configurar os parametros e executar o sql
            stmt.setInt(1, id);

            //Permite trabalhar com os dados recebidos
            ResultSet resultadoConsultaID = stmt.executeQuery();

            // Como o cursor contabiliza antes do primeiro registro é necessário usar o next para acessar o proximo registro
            // nesse caso seria o primeiro e unico resultado.
            if (resultadoConsultaID.next()) {
                int idResult = resultadoConsultaID.getInt("id_cliente");
                String nomeResult = resultadoConsultaID.getString("nome");
                String telefoneResult = resultadoConsultaID.getString("telefone");
                return new Cliente(nomeResult, telefoneResult, idResult);
            }
            throw new ClienteNaoEncontrado(id);
        } catch (SQLException e) {
            System.out.println("Erro na consulta!" + e.getErrorCode());
        }
        return null;
    }

    public static Cliente recuperarPorNome(String nome) {

        String sqlBuscaPorNome = """
                SELECT id_cliente, nome, telefone
                    FROM TB_CLIENTES
                        WHERE UPPER(nome) = UPPER(?)
                """;
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sqlBuscaPorNome);
            stmt.setString(1, nome);
            ResultSet resultadoConsultaNome = stmt.executeQuery();

            if (resultadoConsultaNome.next()) {
                String nomeResult = resultadoConsultaNome.getString("nome");
                String telefoneResult = resultadoConsultaNome.getString("telefone");
                int idResult = resultadoConsultaNome.getInt("id_cliente");
                return new Cliente(nomeResult, telefoneResult, idResult);
            }

            throw new ClienteNaoEncontradoNome(nome);

        } catch (SQLException e) {
            System.out.println("Erro na consulta!" + e.getErrorCode());
        }
        return null;
    }

    public static String excluirClienteId(int id) {

        String sqlExclusaoId = """
                
                delete from tb_clientes
                    where id_cliente = ?
                """;
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sqlExclusaoId);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return """
                    Usuario deletado!
                    """;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String incluirClienteLote(ArrayList<Cliente> clientes){

        String sqlInsertClientes = """
                   INSERT INTO TB_CLIENTES (id_cliente, nome, telefone)
                    VALUES(SEQ_TB_CLIENTE.NEXTVAL, ?, ?)
                """;

        int quantidadeInserida = 0;

        try{
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sqlInsertClientes);
            for(Cliente cli : clientes){
                stmt.setString(1, cli.getNome());
                stmt.setString(2, cli.getTelefone());
                int resultado = stmt.executeUpdate();

                    if(resultado ==1){
                        quantidadeInserida++;
                    }
            }

            if(quantidadeInserida == clientes.size()){
                return "Todos os " + quantidadeInserida + " clientes foram inseridos!";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }




}





