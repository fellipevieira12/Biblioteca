package visao.telas;

import controle.ReservaControle;
import modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaEditarReserva extends JFrame {

    public TelaEditarReserva(int idBibliotecaria) {
        setTitle("Editar Reserva");
        setSize(420, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Reserva> reservas = ReservaControle.listarTodasReservas();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        JLabel lblReserva = new JLabel("Reserva:");
        JComboBox<String> comboReserva = new JComboBox<>(
                reservas.isEmpty()
                        ? new String[]{"Nenhuma reserva cadastrada"}
                        : reservas.stream()
                            .map(r -> r.getIdReserva() + " - " + (r.getLivro() != null ? r.getLivro().getTitulo() : "-"))
                            .toArray(String[]::new)
        );
        comboReserva.setEnabled(!reservas.isEmpty());

        JLabel lblData = new JLabel("Nova Data (dd/MM/yyyy):");
        JTextField txtData = new JTextField();

        Runnable preencherCampos = () -> {
            if (!reservas.isEmpty()) {
                Reserva selecionada = reservas.get(comboReserva.getSelectedIndex());
                if (selecionada.getDataReserva() != null) {
                    txtData.setText(sdf.format(selecionada.getDataReserva()));
                }
            }
        };
        preencherCampos.run();
        comboReserva.addActionListener(e -> preencherCampos.run());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            Integer id = reservas.isEmpty() ? null : reservas.get(comboReserva.getSelectedIndex()).getIdReserva();
            ReservaControle.editarReserva(id, txtData.getText().trim(), this, idBibliotecaria);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuBibliotecaria(idBibliotecaria);
        });

        painel.add(lblReserva); painel.add(comboReserva);
        painel.add(lblData); painel.add(txtData);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}