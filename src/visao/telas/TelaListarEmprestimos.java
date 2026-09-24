package visao.telas;

import controle.EmprestimoControle;
import modelo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaListarEmprestimos extends JFrame {

    public TelaListarEmprestimos(int idBibliotecaria) {
        setTitle("Listar Empréstimos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"ID", "Livro", "Usuário", "Data Empréstimo", "Ativo"};
        List<Emprestimo> emprestimos = EmprestimoControle.listarTodosEmprestimos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Object[][] dados = new Object[emprestimos.size()][5];
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo emp = emprestimos.get(i);
            dados[i][0] = emp.getIdEmprestimo();
            dados[i][1] = emp.getLivro() != null ? emp.getLivro().getTitulo() : "-";
            dados[i][2] = emp.getUsuario() != null ? emp.getUsuario().getNome() : "-";
            dados[i][3] = emp.getDataEmprestimo() != null ? sdf.format(emp.getDataEmprestimo()) : "-";
            dados[i][4] = Boolean.TRUE.equals(emp.getAtivo()) ? "Sim" : "Não";
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