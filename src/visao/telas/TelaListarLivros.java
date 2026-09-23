package visao.telas;

import controle.LivroControle;
import modelo.Livro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaListarLivros extends JFrame {

    public TelaListarLivros(int idBibliotecaria) {
        setTitle("Listar Livros");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"ID", "Título", "Autor", "Status"};
        List<Livro> livros = LivroControle.listarTodosLivros();

        Object[][] dados = new Object[livros.size()][4];
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            dados[i][0] = l.getIdLivro();
            dados[i][1] = l.getTitulo();
            dados[i][2] = l.getAutor();
            dados[i][3] = l.getStatus();
        }

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        JPanel painelBotao = new JPanel();
        painelBotao.add(btnVoltar);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);

        setVisible(true);
    }
}