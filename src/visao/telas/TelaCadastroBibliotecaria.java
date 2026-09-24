package visao.telas;

import controle.BibliotecariaControle;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroBibliotecaria extends JFrame {

    public TelaCadastroBibliotecaria(int idBibliotecaria) {
        setTitle("Cadastrar Bibliotecária");
        setSize(350, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e ->
                BibliotecariaControle.cadastrarBibliotecaria(txtNome.getText().trim(), this, idBibliotecaria)
        );

        painel.add(lblNome); painel.add(txtNome);
        painel.add(btnVoltar); painel.add(btnCadastrar);

        add(painel);
        setVisible(true);
    }
}