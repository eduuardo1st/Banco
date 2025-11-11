import model.Cliente;
import view.BancoView;
import controller.ClienteController;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Sistema Bancário MVC ---");

        Cliente cliente1 = new Cliente("Ana Souza", "111.222.333-44", "C-001", 100.0);

        BancoView view = new BancoView();

        ClienteController controller = new ClienteController(cliente1, view);

        controller.atualizarView();

        System.out.println("\n--- Simulação de Operações ---");

        controller.depositar(500.0);
        controller.sacar(200.0);

        controller.sacar(1000.0);
        controller.sacar(350.0);

        System.out.println("--- Fim da Simulação ---");
    }
}