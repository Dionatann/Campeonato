package campeonato;
// usando coleções do java
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo gerenciamento de times.
 */
public class GerenciadorDeTimes {

    // lista que vai armazenar os times
    private List<Time> times;

    // contrutor da classe
    public GerenciadorDeTimes() {
        times = new ArrayList<>();
    }

    public void addTime(String nome) {
        times.add(new Time(nome));
    }

    public void removerTime(String nome) {
        // a lista 'times' aciona a function padrao para remover o item da lista (removeIf)
        times.removeIf(time -> time.getNome().equals(nome));
    }

    // retorna a lista com os times
    public List<Time> listarTimes() {
        return times;
    }

    // retorna se o tiver esta na lista ou não pelo paramentro do nome do time
    public Time buscarTimePorNome(String nome) {
        for (Time time : times) {
            if (time.getNome().equals(nome)) {
                return time;
            }
        }
        return null;
    }
}
