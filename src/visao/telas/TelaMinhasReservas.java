package visao.telas;

import controle.EmprestimoControle;
import modelo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class TelaMinhasReservas extends JFrame {

    public TelaMinhasReservas(int idUsuario) {
        setTitle("Minhas Reservas");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Emprestimo> emprestimos = EmprestimoControle.listarEmprestimosAtivos().stream()
                .filter(e -> e.getUsuario() != null && e.getUsuario().getIdUsuario() == idUsuario)
                .collect(Collectors.toList());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        if (emprestimos.isEmpty()) {
            modeloLista.addElement("Nenhum empréstimo encontrado.");
        } else {
            for (Emprestimo e : emprestimos) {
                modeloLista.addElement(e.getLivro().getTitulo() + " - Emprestado em " + sdf.format(e.getDataEmprestimo()));
            }
        }

        JList<String> listaReservas = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaReservas);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuUsuario(idUsuario);
        });

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        add(painel);
        setVisible(true);
    }
}
