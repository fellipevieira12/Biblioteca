package visao.telas;

import controle.ReservaControle;
import modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaExcluirReserva extends JFrame {

    public TelaExcluirReserva(int idBibliotecaria) {
        setTitle("Excluir Reserva");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Reserva> reservas = ReservaControle.listarTodasReservas();

        JLabel lblReserva = new JLabel("Reserva:");
        JComboBox<String> comboReserva = new JComboBox<>(
                reservas.isEmpty()
                        ? new String[]{"Nenhuma reserva cadastrada"}
                        : reservas.stream()
                            .map(r -> r.getIdReserva() + " - " + (r.getLivro() != null ? r.getLivro().getTitulo() : "-"))
                            .toArray(String[]::new)
        );
        comboReserva.setEnabled(!reservas.isEmpty());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            Integer id = reservas.isEmpty() ? null : reservas.get(comboReserva.getSelectedIndex()).getIdReserva();
            ReservaControle.excluirReserva(id, this, idBibliotecaria);
        });

        painel.add(lblReserva); painel.add(comboReserva);
        painel.add(btnVoltar); painel.add(btnExcluir);

        add(painel);
        setVisible(true);
    }
}