package agendacontato;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AgendaSwing extends javax.swing.JFrame {

    private Agenda agenda;

    public AgendaSwing(Agenda agenda) {
        this.agenda = agenda;
    }

    public void iniciar() {
        JFrame frame = new JFrame("Agenda de Contatos (Swing)");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 80, 25);
        panel.add(nomeLabel);

        JTextField nomeText = new JTextField(20);
        nomeText.setBounds(100, 20, 165, 25);
        panel.add(nomeText);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(10, 50, 80, 25);
        panel.add(telefoneLabel);

        JTextField telefoneText = new JTextField(20);
        telefoneText.setBounds(100, 50, 165, 25);
        panel.add(telefoneText);

        telefoneText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBounds(10, 90, 360, 100);
        panel.add(buttonPanel);

        JButton adicionarButton = new JButton("Adicionar Contato");
        buttonPanel.add(adicionarButton);

        JButton listarButton = new JButton("Listar Contatos");
        buttonPanel.add(listarButton);

        JButton editarButton = new JButton("Editar Contato");
        buttonPanel.add(editarButton);

        JButton deletarButton = new JButton("Deletar Contato");
        buttonPanel.add(deletarButton);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeText.getText().trim();
                String telefone = telefoneText.getText().trim();
                
                if (nome.isEmpty() || telefone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome e telefone não podem estar vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    agenda.adicionarContato(new Contato(nome, telefone));
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeText.getText();
                String telefone = telefoneText.getText();
                String input = JOptionPane.showInputDialog("Digite o índice do contato para editar:");
                try {
                    int index = Integer.parseInt(input) - 1;
                    Contato contato = agenda.getContato(index);
                    if (contato != null) {
                        contato.setNome(nome);
                        contato.setTelefone(telefone);
                        JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Contato não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Índice inválido.");
                }
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Digite o índice do contato para deletar:");
                try {
                    int index = Integer.parseInt(input) - 1;
                    boolean success = agenda.removerContato(index);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Contato deletado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Contato não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Índice inválido.");
                }
            }
        });
    }
    
    public AgendaSwing() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgendaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaSwing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
