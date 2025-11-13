package model;

/* A classe CLIENTE age como nosso Creator, pois no nosso modelo de negócio, um Cliente possui ou tem uma Conta.
   Uma CONTA não existe por si só, ela está sempre contida num CLIENTE. A classe CLIENTE é a responsável por criar
   um objeto CONTA que irá então realizar suas funções.
*/

import model.Conta.resultadoSaque;

public class Cliente {
    private String nome;
    private String cpf;
    private Conta conta;

    public Cliente(String nome, String cpf, String numeroConta, double limiteConta) {
        this.nome = nome;
        this.cpf = cpf;
        this.conta = new Conta(numeroConta, 0.0, limiteConta);
    }

    public boolean realizarDeposito(double valor) {
        return this.conta.depositar(valor);
    }

    public resultadoSaque realizarSaque(double valor) {
        return this.conta.sacar(valor);
    }

    public resultadoSaque realizarTransferencia(Conta contaDestino, double valor) {
        return this.conta.transferir(contaDestino, valor);
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }
}
