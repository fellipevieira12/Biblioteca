package controle;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.StatusLivro;
import modelo.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmprestimoControle {

    // Como salvar um empréstimo sempre gera uma linha nova no CSV (nunca
    // sobrescreve), pegamos aqui apenas o registro mais recente de cada
    // idEmprestimo para saber o estado atual (ativo ou devolvido).
    public static List<Emprestimo> listarEmprestimosAtivos() {
        Map<Integer, Emprestimo> ultimoRegistroPorId = new LinkedHashMap<>();

        for (Emprestimo emprestimo : ManipuladorArquivos.lerEmprestimos()) {
            ultimoRegistroPorId.put(emprestimo.getIdEmprestimo(), emprestimo);
        }

        return ultimoRegistroPorId.values().stream()
                .filter(Emprestimo::getAtivo)
                .filter(e -> e.getLivro() != null && e.getUsuario() != null)
                .collect(Collectors.toList());
    }

    public static List<Livro> listarLivrosDisponiveis() {
        List<Integer> idsEmprestados = listarEmprestimosAtivos().stream()
                .map(e -> e.getLivro().getIdLivro())
                .collect(Collectors.toList());

        return ManipuladorArquivos.lerLivros().stream()
                .filter(l -> l.getStatus() == StatusLivro.DISPONIVEL && !idsEmprestados.contains(l.getIdLivro()))
                .collect(Collectors.toList());
    }

    public static void registrarEmprestimo(Integer idLivro, Integer idUsuario, JFrame tela, int idBibliotecaria) {
        if (idLivro == null || idUsuario == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um livro e um usuário.");
            return;
        }

        Livro livro = LivroControle.obterLivro(idLivro);
        Usuario usuario = UsuarioControle.obterUsuario(idUsuario);

        int id = ManipuladorArquivos.proximoId("Emprestimo.csv");
        Emprestimo emprestimo = new Emprestimo(id, livro, usuario, new Date());

        BibliotecariaControle.obterBibliotecaria(idBibliotecaria).registrarEmprestimo(emprestimo);
        ManipuladorArquivos.salvarEmprestimo(emprestimo);

        JOptionPane.showMessageDialog(tela, "Empréstimo registrado com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }

    public static void registrarDevolucao(Integer idEmprestimo, JFrame tela, int idBibliotecaria) {
        if (idEmprestimo == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um empréstimo.");
            return;
        }

        Emprestimo emprestimo = listarEmprestimosAtivos().stream()
                .filter(e -> e.getIdEmprestimo() == idEmprestimo)
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            JOptionPane.showMessageDialog(tela, "Empréstimo não encontrado.");
            return;
        }

        BibliotecariaControle.obterBibliotecaria(idBibliotecaria).registrarDevolucao(emprestimo);
        ManipuladorArquivos.salvarEmprestimo(emprestimo);

        JOptionPane.showMessageDialog(tela, "Devolução registrada com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }

    public static void solicitarEmprestimo(Integer idLivro, int idUsuario, JFrame tela) {
        if (idLivro == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um livro.");
            return;
        }

        Livro livro = LivroControle.obterLivro(idLivro);
        Usuario usuario = UsuarioControle.obterUsuario(idUsuario);

        int id = ManipuladorArquivos.proximoId("Emprestimo.csv");
        Emprestimo emprestimo = new Emprestimo(id, livro, usuario, new Date());

        usuario.solicitarEmprestimo();
        livro.alterarStatus(StatusLivro.EMPRESTADO);
        ManipuladorArquivos.salvarEmprestimo(emprestimo);

        JOptionPane.showMessageDialog(tela, "Empréstimo solicitado com sucesso!");
        tela.dispose();
        new visao.menus.MenuUsuario(idUsuario);
    }
}
