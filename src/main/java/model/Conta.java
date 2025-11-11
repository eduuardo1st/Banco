package model;

public class Conta {
    private String numero;
    private double saldo;
    private double limite;

    public Conta(String numero, double saldoInicial, double limite) {
        this.numero = numero;
        this.saldo = saldoInicial;
        this.limite = limite;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && (this.saldo + this.limite) >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    public double getLimite() {
        return limite;
    }
}