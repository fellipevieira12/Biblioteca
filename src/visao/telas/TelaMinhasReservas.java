package visao.telas;

import controle.ReservaControle;
import modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaMinhasReservas extends JFrame {

    public TelaMinhasReservas(int idUsuario) {
        setTitle("Minhas Reservas");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Reserva> reservas = ReservaControle.listarReservasPorUsuario(idUsuario);

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        if (reservas.isEmpty()) {
            modeloLista.addElement("Nenhuma reserva encontrada.");
        } else {
            for (Reserva r : reservas) {
                modeloLista.addElement(r.getLivro().getTitulo() + " - " + r.getStatusReserva());
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
