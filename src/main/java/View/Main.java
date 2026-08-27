package View;

import Conexao.Conexao;
import DAO.ClienteDao;
import Model.Cliente;
import Service.MenuService;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

       Cliente c1 = new Cliente("Matheus", "11993535211");

       int opcaoMenu = 0;
       System.out.println(MenuService.imprimirMenu());
       opcaoMenu = sc.nextInt();
       sc.nextLine();
       while(opcaoMenu != 0){

           switch (opcaoMenu){
               case 1:
                   System.out.println("Cadastro de clientes");
                   System.out.println("Insira o nome: ");
                   String nomeCliente = sc.nextLine();
                   System.out.println("Insira o telefone do cliente: ");
                   String telefoneCliente = sc.nextLine();
                   Cliente cliente = new Cliente(nomeCliente, telefoneCliente);
                   ClienteDao.inserir(cliente);
                   System.out.println(MenuService.imprimirMenu());
                   opcaoMenu = sc.nextInt();
                break;
               case 2:
                   System.out.println("Recuperar todos os clientes!");
                   ClienteDao.recuperarClientes();
                   System.out.println(MenuService.imprimirMenu());
                   opcaoMenu = sc.nextInt();
                break;
               case 3:
                   System.out.println("Pesquisar Clientes\n 1)  ID \n 2) Nome \n 3) Telefone");
                   System.out.println("Digite uma opção: ");
                   int opcaoConsulta = sc.nextInt();
                   sc.nextLine();
                    switch (opcaoConsulta){
                        case 1:
                            System.out.println("Filtrar Clientes por ID\n Digite um ID: ");
                            int idDigitado = sc.nextInt();
                            Cliente clienteiD = ClienteDao.recuperarClientePorId(idDigitado);
                            System.out.println(clienteiD.toString());
                        break;
                    }
                break;

           }





       }












    }
}
