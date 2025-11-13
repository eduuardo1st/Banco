package model;

/* A classe CONTA se encaixa como Information Expert pelo padrão GRASP, pois as funções do nosso "banco"
   só podem ser realizadas pela classe CONTA já que é a única capaz de tomar decisões por possuir
   toda a informação relevante para o algoritmo (SALDO, LIMITE).
*/

public class Conta {
    private String numero;
    private double saldo;
    private double limite;

    public Conta(String numero, double saldoInicial, double limite) {
        this.numero = numero;
        this.saldo = saldoInicial;
        this.limite = limite;
    }

    public enum resultadoSaque {
        SUCESSO,                  // Saldo normal
        SUCESSO_CHEQUE_ESPECIAL,  // Precisou usar o limite
        SALDO_INSUFICIENTE,       // Não tem saldo nem limite
        VALOR_INVALIDO            // Valor negativo ou zero
    }

    public resultadoSaque sacar(double valor) {
        if (valor <= 0) {
            return resultadoSaque.VALOR_INVALIDO;
        }
        // 1 - Tenta sacar do saldo normal
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return resultadoSaque.SUCESSO;
        }
        // 2 - Não tem saldo. Tenta sacar do limite (cheque especial)
        if ((this.saldo + this.limite) >= valor) {
            this.saldo -= valor; // O saldo vai ficar negativo
            return resultadoSaque.SUCESSO_CHEQUE_ESPECIAL;
        }
        // 3. Sem saldo e sem limite
        return resultadoSaque.SALDO_INSUFICIENTE;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public resultadoSaque transferir(Conta destino, double valor) {

        resultadoSaque retornoSaque = this.sacar(valor);

        if (retornoSaque == resultadoSaque.SUCESSO || retornoSaque == resultadoSaque.SUCESSO_CHEQUE_ESPECIAL)
        {
            destino.depositar(valor);
            return retornoSaque;
        }
        return retornoSaque;
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