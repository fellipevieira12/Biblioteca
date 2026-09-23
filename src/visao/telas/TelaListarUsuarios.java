package visao.telas;

import controle.UsuarioControle;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaListarUsuarios extends JFrame {

    public TelaListarUsuarios(int idBibliotecaria) {
        setTitle("Listar Usuários");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"ID", "Nome", "Email"};
        List<Usuario> usuarios = UsuarioControle.listarTodosUsuarios();

        Object[][] dados = new Object[usuarios.size()][3];
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            dados[i][0] = u.getIdUsuario();
            dados[i][1] = u.getNome();
            dados[i][2] = u.getEmail();
        }

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        JPanel painelBotao = new JPanel();
        painelBotao.add(btnVoltar);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);

        setVisible(true);
    }
}