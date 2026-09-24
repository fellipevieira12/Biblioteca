package visao.telas;

import controle.EmprestimoControle;
import modelo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaListarLivrosAtraso extends JFrame {

    public TelaListarLivrosAtraso(int idBibliotecaria) {
        setTitle("Livros em Atraso");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"Livro", "Usuário", "Data Empréstimo", "Dias em Atraso"};
        List<Emprestimo> emprestimosAtrasados = EmprestimoControle.listarEmprestimosEmAtraso();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Object[][] dados = new Object[emprestimosAtrasados.size()][4];
        for (int i = 0; i < emprestimosAtrasados.size(); i++) {
            Emprestimo emp = emprestimosAtrasados.get(i);
            dados[i][0] = emp.getLivro() != null ? emp.getLivro().getTitulo() : "-";
            dados[i][1] = emp.getUsuario() != null ? emp.getUsuario().getNome() : "-";
            dados[i][2] = sdf.format(emp.getDataEmprestimo());
            dados[i][3] = EmprestimoControle.calcularDiasAtraso(emp) + " dia(s)";
        }

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        JLabel lblAviso = new JLabel(
                emprestimosAtrasados.isEmpty()
                        ? "Nenhum livro em atraso no momento."
                        : emprestimosAtrasados.size() + " livro(s) em atraso.",
                SwingConstants.CENTER
        );

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        JPanel painelBotao = new JPanel();
        painelBotao.add(btnVoltar);

        setLayout(new BorderLayout());
        add(lblAviso, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);

        setVisible(true);
    }
}