package visao.telas;

import controle.ReservaControle;
import modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaListarReservasPendentes extends JFrame {

    public TelaListarReservasPendentes(int idBibliotecaria) {
        setTitle("Reservas Pendentes de Efetivação");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = { "ID", "Livro", "Usuário", "Data Reserva" };
        List<Reserva> reservasPendentes = ReservaControle.listarReservasPendentes();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Object[][] dados = new Object[reservasPendentes.size()][4];
        for (int i = 0; i < reservasPendentes.size(); i++) {
            Reserva r = reservasPendentes.get(i);
            dados[i][0] = r.getIdReserva();
            dados[i][1] = r.getLivro() != null ? r.getLivro().getTitulo() : "-";
            dados[i][2] = r.getUsuario() != null ? r.getUsuario().getNome() : "-";
            dados[i][3] = r.getDataReserva() != null ? sdf.format(r.getDataReserva()) : "-";
        }

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        JLabel lblAviso = new JLabel(
                reservasPendentes.isEmpty()
                        ? "Nenhuma reserva pendente no momento."
                        : reservasPendentes.size() + " reserva(s) pendente(s) de efetivação.",
                SwingConstants.CENTER);

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