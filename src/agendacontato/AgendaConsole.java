package agendacontato;

import java.util.Scanner;

public class AgendaConsole {
    
    private Scanner scanner = new Scanner(System.in);
    private Agenda agenda;

    public AgendaConsole(Agenda agenda) {
        this.agenda = agenda;
    }

    public void iniciar() {
        boolean sair = false;

        while (!sair) {
            System.out.println("Agenda de Contatos");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Listar contatos");
            System.out.println("3. Editar contato");
            System.out.println("4. Remover contato");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarContato();
                    break;
                case 2:
                    listarContatos();
                    break;
                case 3:
                    editarContato();
                    break;
                case 4:
                    removerContato();
                    break;
                case 5:
                    sair = true;
                    System.out.println("Saindo da agenda...");
                    break;
                default:
                    System.out.println("Opção invalida! Tente novamente.");
            }
        }
    }

    private void adicionarContato() {
        System.out.print("Digite o nome do contato: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o telefone do contato: ");
        String telefone = scanner.nextLine();

        Contato novoContato = new Contato(nome, telefone);
        agenda.adicionarContato(novoContato);
    }

    private void listarContatos() {
        agenda.listarContatos();
    }

    private void editarContato() {
        if (agenda.tamanho() == 0) {
            System.out.println("A agenda esta vazia.");
            return;
        }
        listarContatos();
        System.out.print("Digite o numero do contato que deseja editar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Digite o novo nome (deixe em branco para manter): ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo telefone (deixe em branco para manter): ");
        String novoTelefone = scanner.nextLine();

        agenda.editarContato(index, novoNome, novoTelefone);
    }

    private void removerContato() {
        if (agenda.tamanho() == 0) {
            System.out.println("A agenda esta vazia.");
            return;
        }
        listarContatos();
        System.out.print("Digite o numero do contato que deseja remover: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        agenda.removerContato(index);
    }
    
}
