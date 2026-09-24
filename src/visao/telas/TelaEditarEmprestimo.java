package visao.telas;

import controle.EmprestimoControle;
import modelo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaEditarEmprestimo extends JFrame {

    public TelaEditarEmprestimo(int idBibliotecaria) {
        setTitle("Editar Empréstimo");
        setSize(420, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Emprestimo> emprestimos = EmprestimoControle.listarTodosEmprestimos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        JLabel lblEmprestimo = new JLabel("Empréstimo:");
        JComboBox<String> comboEmprestimo = new JComboBox<>(
                emprestimos.isEmpty()
                        ? new String[]{"Nenhum empréstimo cadastrado"}
                        : emprestimos.stream()
                            .map(e -> e.getIdEmprestimo() + " - " + (e.getLivro() != null ? e.getLivro().getTitulo() : "-"))
                            .toArray(String[]::new)
        );
        comboEmprestimo.setEnabled(!emprestimos.isEmpty());

        JLabel lblData = new JLabel("Nova Data (dd/MM/yyyy):");
        JTextField txtData = new JTextField();

        Runnable preencherCampos = () -> {
            if (!emprestimos.isEmpty()) {
                Emprestimo selecionado = emprestimos.get(comboEmprestimo.getSelectedIndex());
                if (selecionado.getDataEmprestimo() != null) {
                    txtData.setText(sdf.format(selecionado.getDataEmprestimo()));
                }
            }
        };
        preencherCampos.run();
        comboEmprestimo.addActionListener(e -> preencherCampos.run());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            Integer id = emprestimos.isEmpty() ? null : emprestimos.get(comboEmprestimo.getSelectedIndex()).getIdEmprestimo();
            EmprestimoControle.editarEmprestimo(id, txtData.getText().trim(), this, idBibliotecaria);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblEmprestimo); painel.add(comboEmprestimo);
        painel.add(lblData); painel.add(txtData);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}