package visao.menus;

import controle.UsuarioControle;
import modelo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import visao.telas.TelaMinhasReservas;
import visao.telas.TelaReservarLivro;
import visao.telas.TelaSolicitarEmprestimo;

public class MenuUsuario extends JFrame {
    private int idUsuario;

    public MenuUsuario(int idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Menu Usuário - " + this.idUsuario);
        setSize(400, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnSolicitarEmprestimo = new JButton("Solicitar Empréstimo");
        btnSolicitarEmprestimo.addActionListener(e -> {
            dispose();
            new TelaSolicitarEmprestimo(idUsuario);
        });

        JButton btnReservarLivro = new JButton("Reservar Livro");
        btnReservarLivro.addActionListener(e -> {
            dispose();
            new TelaReservarLivro(idUsuario);
        });

        JButton btnMinhasReservas = new JButton("Minhas Reservas");
        btnMinhasReservas.addActionListener(e -> {
            dispose();
            new TelaMinhasReservas(idUsuario);
        });

        JButton btnHistorico = new JButton("Histórico de Leitura");
        btnHistorico.addActionListener(e -> {
            List<Emprestimo> historico = UsuarioControle.obterHistoricoLeitura(idUsuario);

            if (historico.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Você ainda não concluiu nenhuma leitura.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Emprestimo emp : historico) {
                    sb.append("📖 ").append(emp.getLivro().getTitulo())
                            .append(" - ").append(emp.getLivro().getAutor()).append("\n");
                }

                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                textArea.setOpaque(false);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(300, 200));
                scrollPane.setBorder(BorderFactory.createEmptyBorder());

                JOptionPane.showMessageDialog(this, scrollPane, "Meus Livros Lidos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnSolicitarEmprestimo);
        painel.add(btnReservarLivro);
        painel.add(btnMinhasReservas);
        painel.add(btnHistorico);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}