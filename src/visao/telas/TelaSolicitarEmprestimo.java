package visao.telas;

import controle.EmprestimoControle;
import modelo.Livro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaSolicitarEmprestimo extends JFrame {

    public TelaSolicitarEmprestimo(int idUsuario) {
        setTitle("Solicitar Empréstimo");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Livro> livros = EmprestimoControle.listarLivrosDisponiveis();

        JLabel lblLivro = new JLabel("Livro:");
        JComboBox<String> comboLivro = new JComboBox<>(
                livros.stream()
                        .map(l -> l.getIdLivro() + " - " + l.getTitulo())
                        .toArray(String[]::new)
        );

        JButton btnSolicitar = new JButton("Solicitar");
        btnSolicitar.addActionListener(e -> {
            Integer idLivro = livros.isEmpty() ? null : livros.get(comboLivro.getSelectedIndex()).getIdLivro();
            EmprestimoControle.solicitarEmprestimo(idLivro, idUsuario, this);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuUsuario(idUsuario);
        });

        painel.add(lblLivro); painel.add(comboLivro);
        painel.add(btnVoltar); painel.add(btnSolicitar);

        add(painel);
        setVisible(true);
    }
}
