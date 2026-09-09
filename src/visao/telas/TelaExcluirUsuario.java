package visao.telas;

import controle.UsuarioControle;
import modelo.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaExcluirUsuario extends JFrame {

    public TelaExcluirUsuario(int idBibliotecaria) {
        setTitle("Excluir Usuário");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Usuario> usuarios = ManipuladorArquivos.lerUsuarios();

        JLabel lblUsuario = new JLabel("Usuário:");
        JComboBox<String> comboUsuario = new JComboBox<>(
                usuarios.isEmpty()
                        ? new String[]{"Nenhum usuário cadastrado"}
                        : usuarios.stream()
                            .map(u -> u.getIdUsuario() + " - " + u.getNome())
                            .toArray(String[]::new)
        );
        comboUsuario.setEnabled(!usuarios.isEmpty());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            Integer idUsuario = usuarios.isEmpty() ? null : usuarios.get(comboUsuario.getSelectedIndex()).getIdUsuario();
            UsuarioControle.excluirUsuario(idUsuario, this, idBibliotecaria);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblUsuario); painel.add(comboUsuario);
        painel.add(btnVoltar); painel.add(btnExcluir);

        add(painel);
        setVisible(true);
    }
}
