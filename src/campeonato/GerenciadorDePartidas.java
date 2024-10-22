package campeonato;
// usando coleções do java
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo gerenciamento das partidas.
 */
public class GerenciadorDePartidas {
    private List<Partida> partidas;

    // construtor 
    public GerenciadorDePartidas() {
        partidas = new ArrayList<>();
    }

    public void gerarCronograma(List<Time> times) {
        for (int i = 0; i < times.size(); i++) {
            for (int j = i + 1; j < times.size(); j++) {
                partidas.add(new Partida(times.get(i), times.get(j)));
            }
        }
    }

    public List<Partida> listarPartidas() {
        return partidas;
    }

    public void addResultado(int index, int golsTimeA, int golsTimeB) {
        if (index >= 0 && index < partidas.size()) {
            Partida partida = partidas.get(index);
            if (!partida.isRealizada()) {
                partida.addResultado(golsTimeA, golsTimeB);
            }
        }
    }
}
