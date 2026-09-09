package controle;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Reserva;
import modelo.StatusReserva;
import modelo.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaControle {

    public static List<Reserva> listarReservasPorUsuario(int idUsuario) {
        return ManipuladorArquivos.lerReservas().stream()
                .filter(r -> r.getUsuario() != null && r.getUsuario().getIdUsuario() == idUsuario)
                .filter(r -> r.getLivro() != null)
                .collect(Collectors.toList());
    }

    public static List<Livro> listarLivrosEmprestados() {
        return EmprestimoControle.listarEmprestimosAtivos().stream()
                .map(Emprestimo::getLivro)
                .filter(l -> l != null)
                .collect(Collectors.toList());
    }

    public static void solicitarReserva(Integer idLivro, int idUsuario, JFrame tela) {
        if (idLivro == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um livro.");
            return;
        }

        boolean livroEmprestado = listarLivrosEmprestados().stream()
                .anyMatch(l -> l.getIdLivro() == idLivro);

        if (!livroEmprestado) {
            JOptionPane.showMessageDialog(tela, "Este livro não está emprestado no momento.");
            return;
        }

        boolean jaReservado = listarReservasPorUsuario(idUsuario).stream()
                .anyMatch(r -> r.getStatusReserva() == StatusReserva.ATIVA
                        && r.getLivro() != null && r.getLivro().getIdLivro() == idLivro);

        if (jaReservado) {
            JOptionPane.showMessageDialog(tela, "Você já possui uma reserva ativa para este livro.");
            return;
        }

        Livro livro = LivroControle.obterLivro(idLivro);
        Usuario usuario = UsuarioControle.obterUsuario(idUsuario);

        int id = ManipuladorArquivos.proximoId("Reserva.csv");
        Reserva reserva = new Reserva(id, livro, usuario, new Date());

        usuario.solicitarReserva();
        ManipuladorArquivos.salvarReserva(reserva);

        JOptionPane.showMessageDialog(tela, "Reserva solicitada com sucesso!");
        tela.dispose();
        new visao.menus.MenuUsuario(idUsuario);
    }
}
