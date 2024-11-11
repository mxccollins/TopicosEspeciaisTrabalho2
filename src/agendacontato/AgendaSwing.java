package agendacontato;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AgendaSwing extends JFrame {

    private final Agenda agenda;

    public AgendaSwing(Agenda agenda) {
        this.agenda = agenda;
        initUI();
    }

    private void initUI() {
        setTitle("Agenda de Contatos (Swing)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomeLabel, gbc);

        JTextField nomeText = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nomeText, gbc);

        JLabel telefoneLabel = new JLabel("Telefone:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(telefoneLabel, gbc);

        JTextField telefoneText = new JTextField(20);
        gbc.gridx = 1;
        panel.add(telefoneText, gbc);

        telefoneText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        JButton adicionarButton = new JButton("Adicionar Contato");
        buttonPanel.add(adicionarButton);

        JButton listarButton = new JButton("Listar Contatos");
        buttonPanel.add(listarButton);

        JButton editarButton = new JButton("Editar Contato");
        buttonPanel.add(editarButton);

        JButton deletarButton = new JButton("Deletar Contato");
        buttonPanel.add(deletarButton);

        adicionarButton.addActionListener(e -> adicionarContato(nomeText, telefoneText));
        listarButton.addActionListener(e -> listarContatos());
        editarButton.addActionListener(e -> editarContato(nomeText, telefoneText));
        deletarButton.addActionListener(e -> deletarContato());
    }

    private void adicionarContato(JTextField nomeText, JTextField telefoneText) {
        String nome = nomeText.getText().trim();
        String telefone = telefoneText.getText().trim();
        
        if (nome.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e telefone não podem estar vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            agenda.adicionarContato(new Contato(nome, telefone));
        }
    }

    private void listarContatos() {
        JFrame listaFrame = new JFrame("Lista de Contatos");
        JTextArea textArea = new JTextArea();
        listaFrame.add(new JScrollPane(textArea));

        StringBuilder lista = new StringBuilder();
        for (int i = 0; i < agenda.tamanho(); i++) {
            Contato contato = agenda.getContato(i);
            if (contato != null) {
                lista.append("Contato ").append(i + 1).append(":\n")
                     .append("Nome: ").append(contato.getNome()).append("\n")
                     .append("Telefone: ").append(contato.getTelefone()).append("\n\n");
            }
        }

        textArea.setText(lista.toString());
        listaFrame.setSize(400, 300);
        listaFrame.setVisible(true);
    }

    private void editarContato(JTextField nomeText, JTextField telefoneText) {
        String nome = nomeText.getText();
        String telefone = telefoneText.getText();
        String input = JOptionPane.showInputDialog(this, "Digite o índice do contato para editar:");
        
        try {
            int index = Integer.parseInt(input) - 1;
            Contato contato = agenda.getContato(index);
            if (contato != null) {
                contato.setNome(nome);
                contato.setTelefone(telefone);
                JOptionPane.showMessageDialog(this, "Contato atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Contato não encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Índice inválido.");
        }
    }

    private void deletarContato() {
        String input = JOptionPane.showInputDialog(this, "Digite o índice do contato para deletar:");
        
        try {
            int index = Integer.parseInt(input) - 1;
            boolean success = agenda.removerContato(index);
            if (success) {
                JOptionPane.showMessageDialog(this, "Contato deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Contato não encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Índice inválido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Agenda agenda = new Agenda();
            new AgendaSwing(agenda);
        });
    }
}