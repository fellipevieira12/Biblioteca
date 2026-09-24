package visao.telas;

import controle.BibliotecariaControle;
import modelo.Bibliotecaria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaExcluirBibliotecaria extends JFrame {

    public TelaExcluirBibliotecaria(int idBibliotecaria) {
        setTitle("Excluir Bibliotecária");
        setSize(350, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Bibliotecaria> bibliotecarias = BibliotecariaControle.listarTodasBibliotecarias();

        JLabel lblBibliotecaria = new JLabel("Bibliotecária:");
        JComboBox<String> comboBibliotecaria = new JComboBox<>(
                bibliotecarias.isEmpty()
                        ? new String[]{"Nenhuma cadastrada"}
                        : bibliotecarias.stream()
                            .map(b -> b.getIdBibliotecaria() + " - " + b.getNome())
                            .toArray(String[]::new)
        );
        comboBibliotecaria.setEnabled(!bibliotecarias.isEmpty());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            Integer id = bibliotecarias.isEmpty() ? null : bibliotecarias.get(comboBibliotecaria.getSelectedIndex()).getIdBibliotecaria();
            BibliotecariaControle.excluirBibliotecaria(id, this, idBibliotecaria);
        });

        painel.add(lblBibliotecaria); painel.add(comboBibliotecaria);
        painel.add(btnVoltar); painel.add(btnExcluir);

        add(painel);
        setVisible(true);
    }
}