package visao.telas;

import controle.LivroControle;
import modelo.Livro;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaExcluirLivro extends JFrame {

    public TelaExcluirLivro(int idBibliotecaria) {
        setTitle("Excluir Livro");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Livro> livros = ManipuladorArquivos.lerLivros();

        JLabel lblLivro = new JLabel("Livro:");
        JComboBox<String> comboLivro = new JComboBox<>(
                livros.isEmpty()
                        ? new String[]{"Nenhum livro cadastrado"}
                        : livros.stream()
                            .map(l -> l.getIdLivro() + " - " + l.getTitulo())
                            .toArray(String[]::new)
        );
        comboLivro.setEnabled(!livros.isEmpty());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            Integer idLivro = livros.isEmpty() ? null : livros.get(comboLivro.getSelectedIndex()).getIdLivro();
            LivroControle.excluirLivro(idLivro, this, idBibliotecaria);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblLivro); painel.add(comboLivro);
        painel.add(btnVoltar); painel.add(btnExcluir);

        add(painel);
        setVisible(true);
    }
}
