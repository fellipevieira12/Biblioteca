package visao.telas;

import controle.UsuarioControle;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroUsuario extends JFrame {

    public TelaCadastroUsuario(int idBibliotecaria) {
        setTitle("Cadastro de Usuário");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e ->
            UsuarioControle.cadastrarUsuario(
                txtNome.getText().trim(),
                txtEmail.getText().trim(),
                this,
                idBibliotecaria
            )
        );

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblEmail); painel.add(txtEmail);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}
