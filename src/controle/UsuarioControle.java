package controle;

import modelo.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class UsuarioControle {

    public static void cadastrarUsuario(String nome, String email, JFrame tela, int idBibliotecaria) {
        if (nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos.");
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(tela, "Email inválido.");
            return;
        }

        int id = ManipuladorArquivos.proximoId("Usuario.csv");
        Usuario usuario = new Usuario(id, nome, email);
        BibliotecariaControle.obterBibliotecaria(idBibliotecaria).cadastrarUsuario(usuario);

        JOptionPane.showMessageDialog(tela, "Usuário cadastrado com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }

    public static Usuario obterUsuario(int idUsuario) {
        List<Usuario> usuarios = ManipuladorArquivos.lerUsuarios();
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == idUsuario)
                .findFirst()
                .orElse(null);
    }

    public static void excluirUsuario(Integer idUsuario, JFrame tela, int idBibliotecaria) {
        if (idUsuario == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um usuário.");
            return;
        }

        boolean possuiEmprestimoAtivo = EmprestimoControle.listarEmprestimosAtivos().stream()
                .anyMatch(e -> e.getUsuario() != null && e.getUsuario().getIdUsuario() == idUsuario);

        if (possuiEmprestimoAtivo) {
            JOptionPane.showMessageDialog(tela, "Não é possível excluir: usuário possui empréstimo ativo.");
            return;
        }

        ManipuladorArquivos.removerUsuario(idUsuario);

        JOptionPane.showMessageDialog(tela, "Usuário excluído com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }
}
