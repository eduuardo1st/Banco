import java.util.Scanner;
import model.Cliente;
import view.BancoView;
import controller.ClienteController;

import utils.Validador;

/* Dentro do nosso projeto, procuramos ser simples e fazer algo o mais fácil possível, mas explorando algo novo.
   Nesse caso foi a integração de um módulo extra utils para utilizar um validador do texto de entrada do usuário
   para o CPF e número da conta. Nunca havíamos utilizado e queríamos testar.

   Do GRASP, focamos em criar um Information Expert (Conta) e um Creator (Cliente). Além de que tetamos manter o código minimamente
   coeso (O que talvez poderia ser considerado High Cohesion) e, por usar MVC, temos um controller também, mas ele está sendo utilizado
   de forma mais simplória neste projeto.
*/

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Bem-vindo ao Banco MVC ---");
        System.out.println("Vamos criar sua conta.");

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();


        String cpf;
        while (true) {
            System.out.print("Digite seu CPF (formato xxx.xxx.xxx-xx): ");
            cpf = scanner.nextLine();

            if (Validador.validarFormatoCPF(cpf)) {
                break;
            } else {
                System.out.println("[ERRO] Formato de CPF inválido. Use o padrão xxx.xxx.xxx-xx");
            }
        }

        String numeroConta;
        while (true) {
            System.out.print("Digite o número da conta (formato C-xxx): ");
            numeroConta = scanner.nextLine();

            if (Validador.validarFormatoConta(numeroConta)) {
                break;
            } else {
                System.out.println("[ERRO] Formato de conta inválido. Use o padrão C-xxx (ex: C-001)");
            }
        }

        double limite = 0.0;
        boolean limiteValido = false;
        while (!limiteValido) {
            try {
                System.out.print("Defina seu limite de cheque especial: R$ ");
                limite = Double.parseDouble(scanner.nextLine());
                limiteValido = true;
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Valor inválido. Digite apenas números (ex: 100.50).");
            }
        }

        Cliente cliente1 = new Cliente(nome, cpf, numeroConta, limite);
        BancoView view1 = new BancoView();
        ClienteController usuario = new ClienteController(cliente1, view1);

        // cliente 2 fixo para facilitar a transferência
        Cliente cliente_exemplo = new Cliente("Beto Exemplo da Silva", "555.666.777-88", "C-100", 50.0);
        BancoView view2 = new BancoView();
        ClienteController controller_exemplo = new ClienteController(cliente_exemplo, view2);

        System.out.println("\n--- Conta criada com sucesso! ---");
        System.out.println("Bem-vindo(a), " + cliente1.getNome() + "!");
        System.out.println();

        usuario.atualizarView();

        boolean executando = true;

        while (executando) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Transferir para " + cliente_exemplo.getNome());
            System.out.println("4. Ver status de sua conta");
            System.out.println("5. Ver Status da Conta Destino (" + cliente_exemplo.getNome() + ")");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(scanner.nextLine()); // Mais seguro
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Opção inválida. Digite apenas um número.");
                continue;
            }

            double valor;

            // usamos o parseDouble para garantir a não ocorrência de alguns bugs e comportamento inesperado.

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("Digite o valor para depósito: R$ ");
                        valor = Double.parseDouble(scanner.nextLine());
                        usuario.depositar(valor);
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor inválido.");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Digite o valor para saque: R$ ");
                        valor = Double.parseDouble(scanner.nextLine());
                        usuario.sacar(valor);
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor inválido.");
                    }
                    break;

                case 3: // Transferência
                    try {
                        System.out.print("Digite o valor para transferir para " + cliente_exemplo.getNome() + ": R$ ");
                        valor = Double.parseDouble(scanner.nextLine());

                        usuario.transferir(controller_exemplo, valor);

                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor inválido.");
                    }
                    break;

                case 4:
                    System.out.println("--- Status de sua conta ---");
                    usuario.atualizarView();
                    break;

                case 5:
                    System.out.println("--- Status da conta destino ---");
                    controller_exemplo.atualizarView();
                    break;

                case 0:
                    executando = false;
                    System.out.println("Obrigado por usar nosso sistema!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}