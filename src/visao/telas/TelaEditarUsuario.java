package visao.telas;

import controle.UsuarioControle;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEditarUsuario extends JFrame {

    public TelaEditarUsuario(int idBibliotecaria) {
        setTitle("Editar Usuário");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Usuario> usuarios = UsuarioControle.listarTodosUsuarios();

        JLabel lblUsuario = new JLabel("Usuário:");
        JComboBox<String> comboUsuario = new JComboBox<>(
                usuarios.isEmpty()
                        ? new String[]{"Nenhum usuário cadastrado"}
                        : usuarios.stream()
                            .map(u -> u.getIdUsuario() + " - " + u.getNome())
                            .toArray(String[]::new)
        );
        comboUsuario.setEnabled(!usuarios.isEmpty());

        JLabel lblNome = new JLabel("Novo Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEmail = new JLabel("Novo Email:");
        JTextField txtEmail = new JTextField();

        // Preenche os campos automaticamente com os dados do usuário selecionado
        Runnable preencherCampos = () -> {
            if (!usuarios.isEmpty()) {
                Usuario selecionado = usuarios.get(comboUsuario.getSelectedIndex());
                txtNome.setText(selecionado.getNome());
                txtEmail.setText(selecionado.getEmail());
            }
        };
        preencherCampos.run();
        comboUsuario.addActionListener(e -> preencherCampos.run());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            Integer idUsuario = usuarios.isEmpty() ? null : usuarios.get(comboUsuario.getSelectedIndex()).getIdUsuario();
            UsuarioControle.editarUsuario(
                    idUsuario,
                    txtNome.getText().trim(),
                    txtEmail.getText().trim(),
                    this,
                    idBibliotecaria
            );
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblUsuario); painel.add(comboUsuario);
        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblEmail); painel.add(txtEmail);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}