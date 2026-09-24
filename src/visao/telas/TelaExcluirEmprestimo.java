package visao.telas;

import controle.EmprestimoControle;
import modelo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaExcluirEmprestimo extends JFrame {

    public TelaExcluirEmprestimo(int idBibliotecaria) {
        setTitle("Excluir Empréstimo");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Emprestimo> emprestimos = EmprestimoControle.listarTodosEmprestimos();

        JLabel lblEmprestimo = new JLabel("Empréstimo:");
        JComboBox<String> comboEmprestimo = new JComboBox<>(
                emprestimos.isEmpty()
                        ? new String[]{"Nenhum empréstimo cadastrado"}
                        : emprestimos.stream()
                            .map(e -> e.getIdEmprestimo() + " - " + (e.getLivro() != null ? e.getLivro().getTitulo() : "-"))
                            .toArray(String[]::new)
        );
        comboEmprestimo.setEnabled(!emprestimos.isEmpty());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            Integer id = emprestimos.isEmpty() ? null : emprestimos.get(comboEmprestimo.getSelectedIndex()).getIdEmprestimo();
            EmprestimoControle.excluirEmprestimo(id, this, idBibliotecaria);
        });

        painel.add(lblEmprestimo); painel.add(comboEmprestimo);
        painel.add(btnVoltar); painel.add(btnExcluir);

        add(painel);
        setVisible(true);
    }
}