package visao.telas;

import controle.EmprestimoControle;
import modelo.Livro;
import modelo.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaRegistrarEmprestimo extends JFrame {

    public TelaRegistrarEmprestimo(int idBibliotecaria) {
        setTitle("Registrar Empréstimo");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Livro> livros = EmprestimoControle.listarLivrosDisponiveis();
        List<Usuario> usuarios = ManipuladorArquivos.lerUsuarios();

        JLabel lblLivro = new JLabel("Livro:");
        JComboBox<String> comboLivro = new JComboBox<>(
                livros.stream()
                        .map(l -> l.getIdLivro() + " - " + l.getTitulo())
                        .toArray(String[]::new)
        );

        JLabel lblUsuario = new JLabel("Usuário:");
        JComboBox<String> comboUsuario = new JComboBox<>(
                usuarios.stream()
                        .map(u -> u.getIdUsuario() + " - " + u.getNome())
                        .toArray(String[]::new)
        );

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            Integer idLivro = livros.isEmpty() ? null : livros.get(comboLivro.getSelectedIndex()).getIdLivro();
            Integer idUsuario = usuarios.isEmpty() ? null : usuarios.get(comboUsuario.getSelectedIndex()).getIdUsuario();
            EmprestimoControle.registrarEmprestimo(idLivro, idUsuario, this, idBibliotecaria);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblLivro); painel.add(comboLivro);
        painel.add(lblUsuario); painel.add(comboUsuario);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}
