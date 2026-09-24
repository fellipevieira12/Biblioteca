package visao.menus;

import controle.UsuarioControle;
import modelo.Emprestimo;
import modelo.Usuario;
import util.ManipuladorArquivos;

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

        // 1. Buscar o nome do usuário pelo ID
        String nomeUsuario = "Desconhecido";
        for (Usuario u : ManipuladorArquivos.lerUsuarios()) {
            if (u.getIdUsuario() == this.idUsuario) {
                nomeUsuario = u.getNome();
                break;
            }
        }

        // 2. Definir o título com o nome
        setTitle("Menu Usuário - " + nomeUsuario);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 3. Criar a Barra de Menu principal
        JMenuBar menuBar = new JMenuBar();

        // 4. Criar os Menus principais
        JMenu menuAcoes = new JMenu("Livros & Ações");
        JMenu menuMinhaConta = new JMenu("Minha Conta");
        JMenu menuSistema = new JMenu("Sistema");

        // --- MENU LIVROS & AÇÕES ---
        JMenuItem itemSolicitarEmprestimo = new JMenuItem("Solicitar Empréstimo");
        JMenuItem itemReservarLivro = new JMenuItem("Reservar Livro");
        menuAcoes.add(itemSolicitarEmprestimo);
        menuAcoes.add(itemReservarLivro);

        // --- MENU MINHA CONTA ---
        JMenuItem itemMinhasReservas = new JMenuItem("Minhas Reservas");
        JMenuItem itemHistorico = new JMenuItem("Histórico de Leitura");
        menuMinhaConta.add(itemMinhasReservas);
        menuMinhaConta.add(itemHistorico);

        // --- MENU SISTEMA ---
        JMenuItem itemSair = new JMenuItem("Sair (Voltar ao Menu Inicial)");
        menuSistema.add(itemSair);

        // 5. Adicionar Menus na Barra Principal
        menuBar.add(menuAcoes);
        menuBar.add(menuMinhaConta);
        menuBar.add(menuSistema);

        // Configurar a barra de menu na janela
        setJMenuBar(menuBar);

        // 6. Adicionar o painel central com a imagem "biblioteca.jpg"
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.WHITE); // Fundo branco opcional

        try {
            ImageIcon iconeOriginal = new ImageIcon("imagens/biblioteca.jpg");
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            ImageIcon iconeFinal = new ImageIcon(imagemRedimensionada);

            JLabel labelImagem = new JLabel(iconeFinal, SwingConstants.CENTER);
            painelCentral.add(labelImagem, BorderLayout.CENTER);
        } catch (Exception ex) {
            JLabel labelErro = new JLabel("Imagem biblioteca.jpg não encontrada", SwingConstants.CENTER);
            painelCentral.add(labelErro, BorderLayout.CENTER);
        }

        add(painelCentral);

        // ==========================================
        // 7. Configurar as Ações (ActionListeners)
        // ==========================================

        itemSolicitarEmprestimo.addActionListener(e -> {
            dispose();
            new TelaSolicitarEmprestimo(idUsuario);
        });

        itemReservarLivro.addActionListener(e -> {
            dispose();
            new TelaReservarLivro(idUsuario);
        });

        itemMinhasReservas.addActionListener(e -> {
            dispose();
            new TelaMinhasReservas(idUsuario);
        });

        itemHistorico.addActionListener(e -> {
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

        itemSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        setVisible(true);
    }
}