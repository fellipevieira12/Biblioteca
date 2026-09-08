package visao.telas;

import controle.EmprestimoControle;
import modelo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaRegistrarDevolucao extends JFrame {

    public TelaRegistrarDevolucao(int idBibliotecaria) {
        setTitle("Registrar Devolução");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Emprestimo> emprestimos = EmprestimoControle.listarEmprestimosAtivos();

        JLabel lblEmprestimo = new JLabel("Empréstimo:");
        JComboBox<String> comboEmprestimo = new JComboBox<>(
                emprestimos.stream()
                        .map(e -> e.getIdEmprestimo() + " - " + e.getLivro().getTitulo() + " (" + e.getUsuario().getNome() + ")")
                        .toArray(String[]::new)
        );

        JButton btnConfirmar = new JButton("Confirmar Devolução");
        btnConfirmar.addActionListener(e -> {
            Integer idEmprestimo = emprestimos.isEmpty() ? null : emprestimos.get(comboEmprestimo.getSelectedIndex()).getIdEmprestimo();
            EmprestimoControle.registrarDevolucao(idEmprestimo, this, idBibliotecaria);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblEmprestimo); painel.add(comboEmprestimo);
        painel.add(btnVoltar); painel.add(btnConfirmar);

        add(painel);
        setVisible(true);
    }
}
