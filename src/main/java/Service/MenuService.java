package Service;

public class MenuService {


    public static String imprimirMenu(){

        return String.format("""
                ╔══════════════════════════════════════════╗
                ║              MENU PRINCIPAL              ║
                ╠══════════════════════════════════════════╣
                ║                                          ║
                ║   [1]  Cadastrar cliente                 ║
                ║   [2]  Recuperar todos os clientes       ║
                ║   [3]  Consultar cliente                 ║
                ║   [4]  Excluir cliente                   ║
                ║   [5]  Inserir em lote                   ║
                ║   [0]  Sair                              ║
                ║                                          ║
                ╚══════════════════════════════════════════╝
                """);

    }




}
