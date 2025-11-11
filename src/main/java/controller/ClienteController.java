package controller;

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
            atualizarView(); // Mostra o novo saldo
        } else {
            view.mostrarMensagemErro("Depósito", "Valor inválido");
        }
    }

    public void sacar(double valor) {
        boolean sucesso = model.realizarSaque(valor);
        if (sucesso) {
            view.mostrarMensagemSucesso("Saque", valor);
            atualizarView();
        } else {
            view.mostrarMensagemErro("Saque", "Saldo ou limite insuficiente");
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
}