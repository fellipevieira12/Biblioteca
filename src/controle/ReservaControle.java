package controle;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Reserva;
import modelo.StatusReserva;
import modelo.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static List<Reserva> listarTodasReservas() {
        return ManipuladorArquivos.lerReservas();
    }
 
    public static List<Reserva> listarReservasPendentes() {
        return ManipuladorArquivos.lerReservas().stream()
                .filter(r -> r.getStatusReserva() == StatusReserva.ATIVA)
                .collect(Collectors.toList());
    }
 
    public static void editarReserva(Integer idReserva, String novaDataStr, JFrame tela, int idBibliotecaria) {
        if (idReserva == null) {
            JOptionPane.showMessageDialog(tela, "Selecione uma reserva.");
            return;
        }
 
        if (novaDataStr.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha a data.");
            return;
        }
 
        Date novaData;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            novaData = sdf.parse(novaDataStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(tela, "Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }
 
        List<Reserva> reservas = ManipuladorArquivos.lerReservas();
        Reserva encontrada = null;
 
        for (Reserva r : reservas) {
            if (r.getIdReserva() == idReserva) {
                r.setDataReserva(novaData);
                encontrada = r;
                break;
            }
        }
 
        if (encontrada == null) {
            JOptionPane.showMessageDialog(tela, "Reserva não encontrada.");
            return;
        }
 
        ManipuladorArquivos.reescreverArquivoReservas(reservas);
 
        JOptionPane.showMessageDialog(tela, "Reserva atualizada com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }
 
    public static void excluirReserva(Integer idReserva, JFrame tela, int idBibliotecaria) {
        if (idReserva == null) {
            JOptionPane.showMessageDialog(tela, "Selecione uma reserva.");
            return;
        }
 
        ManipuladorArquivos.removerReserva(idReserva);
 
        JOptionPane.showMessageDialog(tela, "Reserva excluída com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
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
 
