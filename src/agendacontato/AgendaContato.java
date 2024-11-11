package agendacontato;

import java.util.Scanner;

public class AgendaContato {

    public static void main(String[] args) {
        
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha a interface:");
            System.out.println("1. Abrir Console");
            System.out.println("2. Abrir Swing");
            System.out.println("3. Sair");
            System.out.print("Digite sua escolha: ");
            int op = scanner.nextInt();

            switch (op) {
                case 1 -> {
                    AgendaConsole consoleInterface = new AgendaConsole(agenda);
                    consoleInterface.iniciar();
                }
                case 2 -> {
                    javax.swing.SwingUtilities.invokeLater(() -> new AgendaSwing(agenda));
                }
                case 3 -> {
                    System.out.println("Saindo.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Escolha inválida.");
            }
        }
    }
}