package controle;

import modelo.Reserva;
import util.ManipuladorArquivos;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaControle {

    public static List<Reserva> listarReservasPorUsuario(int idUsuario) {
        return ManipuladorArquivos.lerReservas().stream()
                .filter(r -> r.getUsuario() != null && r.getUsuario().getIdUsuario() == idUsuario)
                .filter(r -> r.getLivro() != null)
                .collect(Collectors.toList());
    }
}
