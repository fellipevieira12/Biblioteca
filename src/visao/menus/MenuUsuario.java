package visao.menus;

import javax.swing.*;
import java.awt.*;

public class MenuUsuario extends JFrame {
    private int idUsuario;

    public MenuUsuario(int idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Menu Usuário - " + this.idUsuario);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnSolicitarEmprestimo = new JButton("Solicitar Empréstimo");

        JButton btnMinhasReservas = new JButton("Minhas Reservas");

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnSolicitarEmprestimo);
        painel.add(btnMinhasReservas);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
