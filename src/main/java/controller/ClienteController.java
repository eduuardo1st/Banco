package controller;

import model.Conta;
import model.Conta.resultadoSaque;
import model.Cliente;
import view.BancoView;

public class ClienteController {
    private Cliente model;
    private BancoView view;

    public ClienteController(Cliente model, BancoView view) {
        this.model = model;
        this.view = view;
    }

    public void depositar(double valor) {
        boolean sucesso = model.realizarDeposito(valor);
        if (sucesso) {
            view.mostrarMensagemSucesso("Depósito", valor);
            atualizarView();
            System.out.println();
        } else {
            view.mostrarMensagemErro("Depósito", "Valor inválido");
            System.out.println();
        }
    }

    public void sacar(double valor) {
        resultadoSaque resultado = model.realizarSaque(valor);

        switch (resultado) {
            case SUCESSO:
                view.mostrarMensagemSucesso("Saque", valor);
                atualizarView();
                System.out.println();
                break;

            case SUCESSO_CHEQUE_ESPECIAL:
                view.mostrarMensagemSucesso("Saque (usando cheque especial)", valor);
                atualizarView(); // A view deverá mostrar o valor negativo por ter usado cheque especial no saque
                System.out.println();
                break;

            case SALDO_INSUFICIENTE:
                view.mostrarMensagemErro("Saque", "Saldo e limite insuficientes");
                System.out.println();
                break;

            case VALOR_INVALIDO:
                view.mostrarMensagemErro("Saque", "Valor inválido");
                System.out.println();
                break;
        }
    }

    public void transferir(ClienteController controllerDestino, double valor) {

        Conta contaDestino = controllerDestino.getModel().getConta();

        resultadoSaque resultado = model.realizarTransferencia(contaDestino, valor);

        switch (resultado) {
            case SUCESSO:
                view.mostrarMensagemSucesso("Transferência", valor);
                this.atualizarView();
                controllerDestino.atualizarView();
                System.out.println();
                break;

            case SUCESSO_CHEQUE_ESPECIAL:
                view.mostrarMensagemSucesso("Transferência (usando cheque especial)", valor);
                this.atualizarView();
                controllerDestino.atualizarView();
                System.out.println();
                break;

            case SALDO_INSUFICIENTE:
                view.mostrarMensagemErro("Transferência", "Saldo e limite insuficientes");
                System.out.println();
                break;

            case VALOR_INVALIDO:
                view.mostrarMensagemErro("Transferência", "Valor inválido");
                System.out.println();
                break;
        }
    }

    public void atualizarView() {
        view.mostrarDetalhesCliente(
                model.getNome(),
                model.getConta().getNumero(),
                model.getConta().getSaldo(),
                model.getConta().getLimite()
        );
    }

    public Cliente getModel() {
        return model;
    }

}

