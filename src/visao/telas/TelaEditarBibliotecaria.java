package visao.telas;

import controle.BibliotecariaControle;
import modelo.Bibliotecaria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEditarBibliotecaria extends JFrame {

    public TelaEditarBibliotecaria(int idBibliotecaria) {
        setTitle("Editar Bibliotecária");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
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

        JLabel lblNome = new JLabel("Novo Nome:");
        JTextField txtNome = new JTextField();

        Runnable preencherCampos = () -> {
            if (!bibliotecarias.isEmpty()) {
                Bibliotecaria selecionada = bibliotecarias.get(comboBibliotecaria.getSelectedIndex());
                txtNome.setText(selecionada.getNome());
            }
        };
        preencherCampos.run();
        comboBibliotecaria.addActionListener(e -> preencherCampos.run());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            Integer id = bibliotecarias.isEmpty() ? null : bibliotecarias.get(comboBibliotecaria.getSelectedIndex()).getIdBibliotecaria();
            BibliotecariaControle.editarBibliotecaria(id, txtNome.getText().trim(), this, idBibliotecaria);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblBibliotecaria); painel.add(comboBibliotecaria);
        painel.add(lblNome); painel.add(txtNome);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}