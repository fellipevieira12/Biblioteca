package visao.telas;

import controle.LivroControle;
import modelo.Livro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEditarLivro extends JFrame {

    public TelaEditarLivro(int idBibliotecaria) {
        setTitle("Editar Livro");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Livro> livros = LivroControle.listarTodosLivros();

        JLabel lblLivro = new JLabel("Livro:");
        JComboBox<String> comboLivro = new JComboBox<>(
                livros.isEmpty()
                        ? new String[]{"Nenhum livro cadastrado"}
                        : livros.stream()
                            .map(l -> l.getIdLivro() + " - " + l.getTitulo())
                            .toArray(String[]::new)
        );
        comboLivro.setEnabled(!livros.isEmpty());

        JLabel lblTitulo = new JLabel("Novo Título:");
        JTextField txtTitulo = new JTextField();

        JLabel lblAutor = new JLabel("Novo Autor:");
        JTextField txtAutor = new JTextField();

        // Preenche os campos automaticamente com os dados do livro selecionado
        Runnable preencherCampos = () -> {
            if (!livros.isEmpty()) {
                Livro selecionado = livros.get(comboLivro.getSelectedIndex());
                txtTitulo.setText(selecionado.getTitulo());
                txtAutor.setText(selecionado.getAutor());
            }
        };
        preencherCampos.run();
        comboLivro.addActionListener(e -> preencherCampos.run());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            Integer idLivro = livros.isEmpty() ? null : livros.get(comboLivro.getSelectedIndex()).getIdLivro();
            LivroControle.editarLivro(
                    idLivro,
                    txtTitulo.getText().trim(),
                    txtAutor.getText().trim(),
                    this,
                    idBibliotecaria
            );
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblLivro); painel.add(comboLivro);
        painel.add(lblTitulo); painel.add(txtTitulo);
        painel.add(lblAutor); painel.add(txtAutor);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}