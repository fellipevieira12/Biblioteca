package visao.menus;

import javax.swing.*;
import java.awt.*;

import visao.telas.TelaCadastroLivro;
import visao.telas.TelaCadastroUsuario;
import visao.telas.TelaExcluirLivro;
import visao.telas.TelaExcluirUsuario;
import visao.telas.TelaRegistrarDevolucao;
import visao.telas.TelaRegistrarEmprestimo;

public class MenuBibliotecaria extends JFrame {
    private int idBibliotecaria;

    public MenuBibliotecaria(int idBibliotecaria) {
        this.idBibliotecaria = idBibliotecaria;

        setTitle("Menu Bibliotecária - " + this.idBibliotecaria);
        setSize(400, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCadastrarLivro = new JButton("Cadastrar Livro");
        btnCadastrarLivro.addActionListener(e -> {
            dispose();
            new TelaCadastroLivro(idBibliotecaria);
        });

        JButton btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        btnCadastrarUsuario.addActionListener(e -> {
            dispose();
            new TelaCadastroUsuario(idBibliotecaria);
        });

        JButton btnExcluirLivro = new JButton("Excluir Livro");
        btnExcluirLivro.addActionListener(e -> {
            dispose();
            new TelaExcluirLivro(idBibliotecaria);
        });

        JButton btnExcluirUsuario = new JButton("Excluir Usuário");
        btnExcluirUsuario.addActionListener(e -> {
            dispose();
            new TelaExcluirUsuario(idBibliotecaria);
        });

        JButton btnRegistrarEmprestimo = new JButton("Registrar Empréstimo");
        btnRegistrarEmprestimo.addActionListener(e -> {
            dispose();
            new TelaRegistrarEmprestimo(idBibliotecaria);
        });

        JButton btnRegistrarDevolucao = new JButton("Registrar Devolução");
        btnRegistrarDevolucao.addActionListener(e -> {
            dispose();
            new TelaRegistrarDevolucao(idBibliotecaria);
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnCadastrarLivro);
        painel.add(btnCadastrarUsuario);
        painel.add(btnExcluirLivro);
        painel.add(btnExcluirUsuario);
        painel.add(btnRegistrarEmprestimo);
        painel.add(btnRegistrarDevolucao);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
