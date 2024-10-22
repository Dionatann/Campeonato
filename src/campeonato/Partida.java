package campeonato;
import java.io.Serializable;

/**
 * Classe que representa uma partida entre dois times.
 */
public class Partida implements Serializable {
    private Time timeA;
    private Time timeB;
    private int golsTimeA;
    private int golsTimeB;
    private boolean realizada;
    
    // construtor
    public Partida(Time timeA, Time timeB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.realizada = false;
    }

    public void addResultado(int golsTimeA, int golsTimeB) {
        this.golsTimeA = golsTimeA;
        this.golsTimeB = golsTimeB;
        realizarPartida();
    }

    private void realizarPartida() {
        timeA.registrarGols(golsTimeA, golsTimeB);
        timeB.registrarGols(golsTimeB, golsTimeA);

        // time a -> vencedor
        if (golsTimeA > golsTimeB) {
            timeA.adicionarVitoria();
            timeB.adicionarDerrota();
        // time b vencedor        
        } else if (golsTimeA < golsTimeB) {
            timeB.adicionarVitoria();
            timeA.adicionarDerrota();
        // empate    
        } else {
            timeA.adicionarEmpate();
            timeB.adicionarEmpate();
        }
        // confirma que a partida foi realizada
        this.realizada = true;
    }

    @Override // herdada do Object 
    public String toString() {
        return timeA.getNome() + " " + golsTimeA + " x " + golsTimeB + " " + timeB.getNome();
    }
    // retorna True ou False, indica se foi realizada ou não
    public boolean isRealizada() {
        return realizada;
    }
}