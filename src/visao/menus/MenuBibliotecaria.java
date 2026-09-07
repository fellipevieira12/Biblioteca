package visao.menus;

import javax.swing.*;
import java.awt.*;

import visao.telas.TelaCadastroLivro;
import visao.telas.TelaCadastroUsuario;

public class MenuBibliotecaria extends JFrame {
    private int idBibliotecaria;

    public MenuBibliotecaria(int idBibliotecaria) {
        this.idBibliotecaria = idBibliotecaria;

        setTitle("Menu Bibliotecária - " + this.idBibliotecaria);
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
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

        JButton btnRegistrarEmprestimo = new JButton("Registrar Empréstimo");

        JButton btnRegistrarDevolucao = new JButton("Registrar Devolução");

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnCadastrarLivro);
        painel.add(btnCadastrarUsuario);
        painel.add(btnRegistrarEmprestimo);
        painel.add(btnRegistrarDevolucao);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
