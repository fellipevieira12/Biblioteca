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
        setSize(800, 600); // Janela mais larga, ideal para barras de menu
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Criar a Barra de Menu principal
        JMenuBar menuBar = new JMenuBar();

        // 2. Criar os Menus principais
        JMenu menuCadastros = new JMenu("Cadastros & Edições");
        JMenu menuOperacoes = new JMenu("Operações");
        JMenu menuRelatorios = new JMenu("Relatórios");
        JMenu menuSistema = new JMenu("Sistema");

        // --- MENU CADASTROS & EDIÇÕES ---
        JMenu subMenuLivros = new JMenu("Livros");
        JMenuItem itemCadastrarLivro = new JMenuItem("Cadastrar Livro");
        JMenuItem itemEditarLivro = new JMenuItem("Editar Livro");
        JMenuItem itemExcluirLivro = new JMenuItem("Excluir Livro");
        subMenuLivros.add(itemCadastrarLivro);
        subMenuLivros.add(itemEditarLivro);
        subMenuLivros.add(itemExcluirLivro);

        JMenu subMenuUsuarios = new JMenu("Usuários");
        JMenuItem itemCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
        JMenuItem itemEditarUsuario = new JMenuItem("Editar Usuário");
        JMenuItem itemExcluirUsuario = new JMenuItem("Excluir Usuário");
        subMenuUsuarios.add(itemCadastrarUsuario);
        subMenuUsuarios.add(itemEditarUsuario);
        subMenuUsuarios.add(itemExcluirUsuario);

        JMenu subMenuBibliotecarias = new JMenu("Bibliotecárias");
        JMenuItem itemCadastrarBiblio = new JMenuItem("Cadastrar Bibliotecária");
        JMenuItem itemEditarBiblio = new JMenuItem("Editar Bibliotecária");
        JMenuItem itemExcluirBiblio = new JMenuItem("Excluir Bibliotecária");
        subMenuBibliotecarias.add(itemCadastrarBiblio);
        subMenuBibliotecarias.add(itemEditarBiblio);
        subMenuBibliotecarias.add(itemExcluirBiblio);

        menuCadastros.add(subMenuLivros);
        menuCadastros.add(subMenuUsuarios);
        menuCadastros.add(subMenuBibliotecarias);

        // --- MENU OPERAÇÕES ---
        JMenu subMenuEmprestimos = new JMenu("Empréstimos");
        JMenuItem itemRegEmprestimo = new JMenuItem("Registrar Empréstimo");
        JMenuItem itemRegDevolucao = new JMenuItem("Registrar Devolução");
        JMenuItem itemEditarEmprestimo = new JMenuItem("Editar Empréstimo");
        JMenuItem itemExcluirEmprestimo = new JMenuItem("Excluir Empréstimo");
        subMenuEmprestimos.add(itemRegEmprestimo);
        subMenuEmprestimos.add(itemRegDevolucao);
        subMenuEmprestimos.addSeparator(); // Adiciona uma linha divisória
        subMenuEmprestimos.add(itemEditarEmprestimo);
        subMenuEmprestimos.add(itemExcluirEmprestimo);

        JMenu subMenuReservas = new JMenu("Reservas");
        JMenuItem itemEditarReserva = new JMenuItem("Editar Reserva");
        JMenuItem itemExcluirReserva = new JMenuItem("Excluir Reserva");
        subMenuReservas.add(itemEditarReserva);
        subMenuReservas.add(itemExcluirReserva);

        menuOperacoes.add(subMenuEmprestimos);
        menuOperacoes.add(subMenuReservas);

        // --- MENU RELATÓRIOS ---
        JMenuItem itemListarLivros = new JMenuItem("Listar Livros");
        JMenuItem itemLivrosAtraso = new JMenuItem("Livros em Atraso");
        JMenuItem itemListarUsuarios = new JMenuItem("Listar Usuários");
        JMenuItem itemListarBiblio = new JMenuItem("Listar Bibliotecárias");
        JMenuItem itemListarEmprestimos = new JMenuItem("Listar Empréstimos");
        JMenuItem itemListarReservas = new JMenuItem("Listar Reservas");
        JMenuItem itemResPendentes = new JMenuItem("Reservas Pendentes");

        menuRelatorios.add(itemListarLivros);
        menuRelatorios.add(itemLivrosAtraso);
        menuRelatorios.addSeparator();
        menuRelatorios.add(itemListarUsuarios);
        menuRelatorios.add(itemListarBiblio);
        menuRelatorios.addSeparator();
        menuRelatorios.add(itemListarEmprestimos);
        menuRelatorios.add(itemListarReservas);
        menuRelatorios.add(itemResPendentes);

        // --- MENU SISTEMA ---
        JMenuItem itemSair = new JMenuItem("Sair (Voltar ao Menu Inicial)");
        menuSistema.add(itemSair);

        // 3. Adicionar Menus na Barra Principal
        menuBar.add(menuCadastros);
        menuBar.add(menuOperacoes);
        menuBar.add(menuRelatorios);
        menuBar.add(menuSistema);

        // Configurar a barra de menu na janela
        setJMenuBar(menuBar);

        // 4. Adicionar o painel central com imagem preenchendo 100%
        ImageIcon icone = new ImageIcon("images/biblioteca.jpg");

        if (icone.getIconWidth() == -1) {
            // Se a imagem não for encontrada, mostra o erro
            JPanel painelErro = new JPanel(new BorderLayout());
            painelErro.add(new JLabel("Imagem images/biblioteca.jpg não encontrada", SwingConstants.CENTER));
            add(painelErro);
        } else {
            // Cria um painel personalizado que redesenha a imagem ocupando todo o espaço
            final Image imagemFundo = icone.getImage();

            JPanel painelCentral = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Desenha a imagem a partir da posição 0,0 até ao limite exato do painel
                    g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
                }
            };

            add(painelCentral);
        }

        // ==========================================
        // 5. Configurar as Ações (ActionListeners)
        // ==========================================

        // Ações de Livros
        itemCadastrarLivro.addActionListener(e -> {
            dispose();
            new TelaCadastroLivro(idBibliotecaria);
        });
        itemEditarLivro.addActionListener(e -> {
            dispose();
            new TelaEditarLivro(idBibliotecaria);
        });
        itemExcluirLivro.addActionListener(e -> {
            dispose();
            new TelaExcluirLivro(idBibliotecaria);
        });

        // Ações de Usuários
        itemCadastrarUsuario.addActionListener(e -> {
            dispose();
            new TelaCadastroUsuario(idBibliotecaria);
        });
        itemEditarUsuario.addActionListener(e -> {
            dispose();
            new TelaEditarUsuario(idBibliotecaria);
        });
        itemExcluirUsuario.addActionListener(e -> {
            dispose();
            new TelaExcluirUsuario(idBibliotecaria);
        });

        // Ações de Bibliotecárias
        itemCadastrarBiblio.addActionListener(e -> {
            dispose();
            new TelaCadastroBibliotecaria(idBibliotecaria);
        });
        itemEditarBiblio.addActionListener(e -> {
            dispose();
            new TelaEditarBibliotecaria(idBibliotecaria);
        });
        itemExcluirBiblio.addActionListener(e -> {
            dispose();
            new TelaExcluirBibliotecaria(idBibliotecaria);
        });

        // Ações de Empréstimos
        itemRegEmprestimo.addActionListener(e -> {
            dispose();
            new TelaRegistrarEmprestimo(idBibliotecaria);
        });
        itemRegDevolucao.addActionListener(e -> {
            dispose();
            new TelaRegistrarDevolucao(idBibliotecaria);
        });
        itemEditarEmprestimo.addActionListener(e -> {
            dispose();
            new TelaEditarEmprestimo(idBibliotecaria);
        });
        itemExcluirEmprestimo.addActionListener(e -> {
            dispose();
            new TelaExcluirEmprestimo(idBibliotecaria);
        });

        // Ações de Reservas
        itemEditarReserva.addActionListener(e -> {
            dispose();
            new TelaEditarReserva(idBibliotecaria);
        });
        itemExcluirReserva.addActionListener(e -> {
            dispose();
            new TelaExcluirReserva(idBibliotecaria);
        });

        // Ações de Relatórios
        itemListarLivros.addActionListener(e -> {
            dispose();
            new TelaListarLivros(idBibliotecaria);
        });
        itemLivrosAtraso.addActionListener(e -> {
            dispose();
            new TelaListarLivrosAtraso(idBibliotecaria);
        });
        itemListarUsuarios.addActionListener(e -> {
            dispose();
            new TelaListarUsuarios(idBibliotecaria);
        });
        itemListarBiblio.addActionListener(e -> {
            dispose();
            new TelaListarBibliotecarias(idBibliotecaria);
        });
        itemListarEmprestimos.addActionListener(e -> {
            dispose();
            new TelaListarEmprestimos(idBibliotecaria);
        });
        itemListarReservas.addActionListener(e -> {
            dispose();
            new TelaListarReservas(idBibliotecaria);
        });
        itemResPendentes.addActionListener(e -> {
            dispose();
            new TelaListarReservasPendentes(idBibliotecaria);
        });

        // Ação de Sair
        itemSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        setVisible(true);
    }
}