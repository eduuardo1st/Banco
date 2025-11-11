package view;

public class BancoView {

    public void mostrarDetalhesCliente(String nome, String numeroConta, double saldo, double limite) {
        System.out.println("--- Detalhes do Cliente ---");
        System.out.println("Cliente: " + nome);
        System.out.println("Conta: " + numeroConta);
        System.out.printf("Saldo: R$ %.2f\n", saldo);
        System.out.printf("Limite: R$ %.2f\n", limite);
        System.out.println("---------------------------");
    }

    public void mostrarMensagemSucesso(String operacao, double valor) {
        System.out.printf("[SUCESSO] %s de R$ %.2f realizado.\n", operacao, valor);
    }

    public void mostrarMensagemErro(String operacao, String motivo) {
        System.out.printf("[FALHA] %s não realizado. Motivo: %s.\n", operacao, motivo);
    }
}