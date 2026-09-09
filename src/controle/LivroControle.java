package controle;

import modelo.Livro;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class LivroControle {

    public static void cadastrarLivro(String titulo, String autor, JFrame tela, int idBibliotecaria) {
        if (titulo.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos.");
            return;
        }

        int id = ManipuladorArquivos.proximoId("Livro.csv");
        Livro livro = new Livro(id, titulo, autor);
        BibliotecariaControle.obterBibliotecaria(idBibliotecaria).cadastrarLivro(livro);

        JOptionPane.showMessageDialog(tela, "Livro cadastrado com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }

    public static Livro obterLivro(int idLivro) {
        List<Livro> livros = ManipuladorArquivos.lerLivros();
        return livros.stream()
                .filter(l -> l.getIdLivro() == idLivro)
                .findFirst()
                .orElse(null);
    }

    public static void excluirLivro(Integer idLivro, JFrame tela, int idBibliotecaria) {
        if (idLivro == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um livro.");
            return;
        }

        boolean emprestado = EmprestimoControle.listarEmprestimosAtivos().stream()
                .anyMatch(e -> e.getLivro() != null && e.getLivro().getIdLivro() == idLivro);

        if (emprestado) {
            JOptionPane.showMessageDialog(tela, "Não é possível excluir: livro está emprestado.");
            return;
        }

        ManipuladorArquivos.removerLivro(idLivro);

        JOptionPane.showMessageDialog(tela, "Livro excluído com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }
}
