package agendacontato;

import java.util.Scanner;

public class AgendaContato {

    public static void main(String[] args) {
        
        
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha a interface:");
        System.out.println("1. Abrir com Console");
        System.out.println("2. Abrir com Swing");
        System.out.println("3. Sair");
        System.out.print("Digite sua escolha: ");
        

        //if (op == 1){
        //    AgendaConsole consoleInterface = new AgendaConsole(agenda);
        //    consoleInterface.iniciar();
        //} else if (op == 2) {
        //    AgendaSwing swingInterface = new AgendaSwing(agenda);
        //    swingInterface.iniciar();
        //} else {
        //    System.out.println("Escolha invalida!");
        //}
        while (true) {
            int op = scanner.nextInt();
            
            switch (op) {
                case 1 -> {
                    AgendaConsole consoleInterface = new AgendaConsole(agenda);
                    consoleInterface.iniciar();
                    return;
                }
                case 2 -> {
                    AgendaSwing swingInterface = new AgendaSwing(agenda);
                    swingInterface.iniciar();
                    return;
                }
                case 3 -> {
                    System.out.println("Saindo... Ate logo.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opcao Invalida. Tente novamente");
            }
            //if (op == 1) {
            //    AgendaConsole consoleInterface = new AgendaConsole(agenda);
            //    consoleInterface.iniciar();
            //    return;
            //} else if (op == 2) {
            //    AgendaSwing swingInterface = new AgendaSwing(agenda);
            //    swingInterface.iniciar();
            //    return;
            //} else if (op == 3) {
            //    System.out.println("Saindo... Ate logo.");
            //    scanner.close();
            //   return;
            //} else {
            //    System.out.println("Opcao Invalida. Tente novamente");
            //}
            
        }
        
    }

}
    

