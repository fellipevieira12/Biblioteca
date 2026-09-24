package visao.menus;

import javax.swing.*;
import java.awt.*;

import visao.telas.TelaCadastroLivro;
import visao.telas.TelaCadastroUsuario;
import visao.telas.TelaCadastroBibliotecaria;
import visao.telas.TelaExcluirLivro;
import visao.telas.TelaExcluirUsuario;
import visao.telas.TelaExcluirBibliotecaria;
import visao.telas.TelaExcluirEmprestimo;
import visao.telas.TelaExcluirReserva;
import visao.telas.TelaRegistrarDevolucao;
import visao.telas.TelaRegistrarEmprestimo;
import visao.telas.TelaListarLivros;
import visao.telas.TelaListarUsuarios;
import visao.telas.TelaListarEmprestimos;
import visao.telas.TelaListarReservas;
import visao.telas.TelaListarBibliotecarias;
import visao.telas.TelaEditarLivro;
import visao.telas.TelaEditarUsuario;
import visao.telas.TelaEditarBibliotecaria;
import visao.telas.TelaEditarEmprestimo;
import visao.telas.TelaEditarReserva;
import visao.telas.TelaListarLivrosAtraso;
import visao.telas.TelaListarReservasPendentes;

public class MenuBibliotecaria extends JFrame {
    private int idBibliotecaria;

    public MenuBibliotecaria(int idBibliotecaria) {
        this.idBibliotecaria = idBibliotecaria;

        setTitle("Menu Bibliotecária - " + this.idBibliotecaria);
        setSize(450, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(23, 1, 6, 6));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

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

        JButton btnCadastrarBibliotecaria = new JButton("Cadastrar Bibliotecária");
        btnCadastrarBibliotecaria.addActionListener(e -> {
            dispose();
            new TelaCadastroBibliotecaria(idBibliotecaria);
        });

        JButton btnEditarLivro = new JButton("Editar Livro");
        btnEditarLivro.addActionListener(e -> {
            dispose();
            new TelaEditarLivro(idBibliotecaria);
        });

        JButton btnEditarUsuario = new JButton("Editar Usuário");
        btnEditarUsuario.addActionListener(e -> {
            dispose();
            new TelaEditarUsuario(idBibliotecaria);
        });

        JButton btnEditarBibliotecaria = new JButton("Editar Bibliotecária");
        btnEditarBibliotecaria.addActionListener(e -> {
            dispose();
            new TelaEditarBibliotecaria(idBibliotecaria);
        });

        JButton btnEditarEmprestimo = new JButton("Editar Empréstimo");
        btnEditarEmprestimo.addActionListener(e -> {
            dispose();
            new TelaEditarEmprestimo(idBibliotecaria);
        });

        JButton btnEditarReserva = new JButton("Editar Reserva");
        btnEditarReserva.addActionListener(e -> {
            dispose();
            new TelaEditarReserva(idBibliotecaria);
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

        JButton btnExcluirBibliotecaria = new JButton("Excluir Bibliotecária");
        btnExcluirBibliotecaria.addActionListener(e -> {
            dispose();
            new TelaExcluirBibliotecaria(idBibliotecaria);
        });

        JButton btnExcluirEmprestimo = new JButton("Excluir Empréstimo");
        btnExcluirEmprestimo.addActionListener(e -> {
            dispose();
            new TelaExcluirEmprestimo(idBibliotecaria);
        });

        JButton btnExcluirReserva = new JButton("Excluir Reserva");
        btnExcluirReserva.addActionListener(e -> {
            dispose();
            new TelaExcluirReserva(idBibliotecaria);
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

        JButton btnListarLivros = new JButton("Listar Livros");
        btnListarLivros.addActionListener(e -> {
            dispose();
            new TelaListarLivros(idBibliotecaria);
        });

        JButton btnListarUsuarios = new JButton("Listar Usuários");
        btnListarUsuarios.addActionListener(e -> {
            dispose();
            new TelaListarUsuarios(idBibliotecaria);
        });

        JButton btnListarBibliotecarias = new JButton("Listar Bibliotecárias");
        btnListarBibliotecarias.addActionListener(e -> {
            dispose();
            new TelaListarBibliotecarias(idBibliotecaria);
        });

        JButton btnListarEmprestimos = new JButton("Listar Empréstimos");
        btnListarEmprestimos.addActionListener(e -> {
            dispose();
            new TelaListarEmprestimos(idBibliotecaria);
        });

        JButton btnListarReservas = new JButton("Listar Reservas");
        btnListarReservas.addActionListener(e -> {
            dispose();
            new TelaListarReservas(idBibliotecaria);
        });

        JButton btnListarLivrosAtraso = new JButton("Livros em Atraso");
        btnListarLivrosAtraso.addActionListener(e -> {
            dispose();
            new TelaListarLivrosAtraso(idBibliotecaria);
        });

        JButton btnListarReservasPendentes = new JButton("Reservas Pendentes");
        btnListarReservasPendentes.addActionListener(e -> {
            dispose();
            new TelaListarReservasPendentes(idBibliotecaria);
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnCadastrarLivro);
        painel.add(btnCadastrarUsuario);
        painel.add(btnCadastrarBibliotecaria);
        painel.add(btnEditarLivro);
        painel.add(btnEditarUsuario);
        painel.add(btnEditarBibliotecaria);
        painel.add(btnEditarEmprestimo);
        painel.add(btnEditarReserva);
        painel.add(btnExcluirLivro);
        painel.add(btnExcluirUsuario);
        painel.add(btnExcluirBibliotecaria);
        painel.add(btnExcluirEmprestimo);
        painel.add(btnExcluirReserva);
        painel.add(btnRegistrarEmprestimo);
        painel.add(btnRegistrarDevolucao);
        painel.add(btnListarLivros);
        painel.add(btnListarUsuarios);
        painel.add(btnListarBibliotecarias);
        painel.add(btnListarEmprestimos);
        painel.add(btnListarReservas);
        painel.add(btnListarLivrosAtraso);
        painel.add(btnListarReservasPendentes);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}