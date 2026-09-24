package visao.telas;

import controle.BibliotecariaControle;
import modelo.Bibliotecaria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaListarBibliotecarias extends JFrame {

    public TelaListarBibliotecarias(int idBibliotecaria) {
        setTitle("Listar Bibliotecárias");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"ID", "Nome"};
        List<Bibliotecaria> bibliotecarias = BibliotecariaControle.listarTodasBibliotecarias();

        Object[][] dados = new Object[bibliotecarias.size()][2];
        for (int i = 0; i < bibliotecarias.size(); i++) {
            Bibliotecaria b = bibliotecarias.get(i);
            dados[i][0] = b.getIdBibliotecaria();
            dados[i][1] = b.getNome();
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