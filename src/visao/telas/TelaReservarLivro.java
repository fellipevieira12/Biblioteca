package visao.telas;

import controle.ReservaControle;
import modelo.Livro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaReservarLivro extends JFrame {

    public TelaReservarLivro(int idUsuario) {
        setTitle("Reservar Livro");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Livro> livros = ReservaControle.listarLivrosEmprestados();

        JLabel lblLivro = new JLabel("Livro emprestado:");
        JComboBox<String> comboLivro = new JComboBox<>(
                livros.isEmpty()
                        ? new String[]{"Nenhum livro emprestado no momento"}
                        : livros.stream()
                            .map(l -> l.getIdLivro() + " - " + l.getTitulo())
                            .toArray(String[]::new)
        );
        comboLivro.setEnabled(!livros.isEmpty());

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> {
            Integer idLivro = livros.isEmpty() ? null : livros.get(comboLivro.getSelectedIndex()).getIdLivro();
            ReservaControle.solicitarReserva(idLivro, idUsuario, this);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuUsuario(idUsuario);
        });

        painel.add(lblLivro); painel.add(comboLivro);
        painel.add(btnVoltar); painel.add(btnReservar);

        add(painel);
        setVisible(true);
    }
}
