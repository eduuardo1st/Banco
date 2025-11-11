package model;

public class Cliente {
    private String nome;
    private String cpf;
    private Conta conta; // Agregação

    public Cliente(String nome, String cpf, String numeroConta, double limiteConta) {
        this.nome = nome;
        this.cpf = cpf;
        this.conta = new Conta(numeroConta, 0.0, limiteConta);
    }

    public boolean realizarDeposito(double valor) {
        return this.conta.depositar(valor);
    }

    public boolean realizarSaque(double valor) {
        return this.conta.sacar(valor);
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }
}
