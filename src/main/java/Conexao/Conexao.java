package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static String driver= "oracle.jdbc.driver.OracleDriver";
    private static String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static String usuario = "rm572228";
    private static String senha = "260497";

    private static Connection conexao;


    public static void loadDriver(){
        try{
            // É como ligar o tradutor para dps se comunicar com o banco.
            Class.forName(driver);
              System.out.println("Driver carregado com sucesso!");
        } catch (ClassNotFoundException e) {
             System.err.println("Erro ao carregar o driver do banco" + e.getMessage());
        }
    }

    public static Connection conectar(){
        try{
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao com o banco oracle estabelecida!");
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco verificque url, usuario e senha" + e.getMessage());
        }
        return null;
    }

    public static void fecharConexao(){

        try{
            if (conexao != null && !conexao.isClosed()){
                 conexao.close();
                System.out.println("Conexao fechada!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
