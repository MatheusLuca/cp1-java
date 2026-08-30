package View;

import Conexao.Conexao;
import DAO.ClienteDao;
import Exceptions.ClienteNaoEncontrado;
import Exceptions.ClienteNaoEncontradoNome;
import Model.Cliente;
import Service.ClienteService;
import Service.MenuService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);





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
                   sc.nextLine();
                break;
               case 3:
                   System.out.println("Pesquisar Clientes\n 1)  ID \n 2) Nome \n (0) Sair menu consulta");
                   System.out.println("Digite uma opção: ");
                   int opcaoConsulta = sc.nextInt();
                   sc.nextLine();
                   boolean sairMenuConsulta = false;

                   while(!sairMenuConsulta){

                       switch (opcaoConsulta){
                           case 1:
                               try{
                                   System.out.println("Filtrar Clientes por ID: ");
                                   int idDigitado = sc.nextInt();
                                   Cliente clienteiD = ClienteDao.recuperarClientePorId(idDigitado);
                                   System.out.println(clienteiD.toString());
                               }catch (ClienteNaoEncontrado e){
                                   System.err.println("Erro: " + e.getMessage());
                               }finally {
                                   System.out.println("Deseja filtrar por (1) ID \nFiltrar por nome (2) \n(O) para sair do menu consulta:\n");
                                   opcaoConsulta = sc.nextInt();
                                   sc.nextLine();
                               }
                               break;
                           case 2:
                               try{
                                   System.out.println("Filtrar por Nome: ");
                                   String nomeDigitado = sc.nextLine();
                                   Cliente clienteNome = ClienteDao.recuperarPorNome(nomeDigitado);
                                   System.out.println(clienteNome.toString());
                               }catch (ClienteNaoEncontradoNome e){
                                   System.err.println("Error: " + e.getMessage());
                               }finally {
                                   System.out.println("Deseja filtrar por (1) ID \nFiltrar por nome (2) \n(O) para sair do menu consulta:\n");
                                   opcaoConsulta = sc.nextInt();
                                   sc.nextLine();

                               }
                               break;
                           case 0:
                               sairMenuConsulta = true;
                               break;
                       }
                   }
                   System.out.println(MenuService.imprimirMenu());
                   opcaoMenu = sc.nextInt();
                break;

               case 4:
                   System.out.println("Insira um ID para deleção: ");
                   int idExclusao = sc.nextInt();

                   System.out.println("Dados do cliente para exclusao: ");
                   Cliente clienteiD = ClienteDao.recuperarClientePorId(idExclusao);
                   System.out.println(clienteiD.toString());

                   System.out.println("Digite s para excluir: ");
                   char confirmarExclusao = sc.next().charAt(0);

                    if(confirmarExclusao == 's' || confirmarExclusao == 'S'){
                        ClienteDao.excluirClienteId(idExclusao);
                        System.out.println("Executou");
                        System.out.println(MenuService.imprimirMenu());
                        opcaoMenu = sc.nextInt();
                    }
                   System.out.println(MenuService.imprimirMenu());
                   opcaoMenu = sc.nextInt();
                   break;

               case 5:

                   boolean pararInclusao = true;

                   while (pararInclusao) {
                       System.out.println("Cadastrar Clientes em lote: ");
                       System.out.println("(1) Para cadastrar (2) sair e salvar lotes");

                       int opcaoInclusao = sc.nextInt();
                       sc.nextLine();

                       if (opcaoInclusao == 1) {

                           System.out.println("Insira o nome: ");
                           String nomeClienteLote = sc.nextLine();

                           System.out.println("Insira o telefone do cliente: ");
                           String telefoneClienteLote = sc.nextLine();

                           Cliente clienteLote = new Cliente(nomeClienteLote, telefoneClienteLote);
                           ClienteService.addCliente(clienteLote);

                       } else if (opcaoInclusao == 2) {
                           ArrayList<Cliente> listaClientes = ClienteService.getClientes();
                           ClienteDao.incluirClienteLote(listaClientes);
                           pararInclusao = false;
                           System.out.println(MenuService.imprimirMenu());
                           opcaoMenu = sc.nextInt();

                       } else {
                           System.out.println("Opção inválida!");
                       }
                   }
                   break;
           }
       }
    }
}

