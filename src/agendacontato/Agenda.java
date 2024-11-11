package agendacontato;

import java.util.ArrayList;

public class Agenda {
    
    private ArrayList<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }
    
    public Contato getContato(int index) {
        if (index >= 0 && index < contatos.size()) {
            return contatos.get(index);
        }
        return null;
    }

    
    public void adicionarContato(Contato contato) {
        contatos.add(contato);
        System.out.println("Novo contato adicionado com sucesso!");
    }
    
   
    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("A agenda esta vazia.");
        } else {
            System.out.println("Exibindo contatos:");
            for (int i = 0; i < contatos.size(); i++) {
                System.out.println("Contato " + (i + 1) + ":");
                contatos.get(i).exibirContato();
            }
        }
    }

    
    public void editarContato(int indice, String novoNome, String novoTelefone) {
        if (indice >= 0 && indice < contatos.size()) {
            Contato contato = contatos.get(indice);
            if (!novoNome.isEmpty()) contato.setNome(novoNome);
            if (!novoTelefone.isEmpty()) contato.setTelefone(novoTelefone);
            System.out.println("Contato atualizado!");
        } else {
            System.out.println("Contato inexistente.");
        }
    }

    
    public boolean removerContato(int index) {
        if (index >= 0 && index < contatos.size()) {
            contatos.remove(index);
            return true;
        }
        return false;
    }

    public int tamanho() {
        return contatos.size();
    }

    Object getContatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
