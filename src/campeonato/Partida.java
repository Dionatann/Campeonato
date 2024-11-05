package campeonato;

import org.json.JSONObject;

public class Partida {
    private Time timeA;
    private Time timeB;
    private int golsTimeA;
    private int golsTimeB;
    private boolean realizada;

    public Partida(Time timeA, Time timeB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.golsTimeA = 0;
        this.golsTimeB = 0;
        this.realizada = false;
    }

    public boolean isRealizada() {
        return realizada;
    }

    // Adiciona o resultado da partida e atualiza os times
    public void addResultado(int golsTimeA, int golsTimeB) {
        if (!realizada) {
            this.golsTimeA = golsTimeA;
            this.golsTimeB = golsTimeB;
            this.realizada = true;

            // Atualiza os times com base no resultado
            if (golsTimeA > golsTimeB) {
                timeA.adicionarVitoria();
                timeB.adicionarDerrota();
            } else if (golsTimeA < golsTimeB) {
                timeB.adicionarVitoria();
                timeA.adicionarDerrota();
            } else {
                timeA.adicionarEmpate();
                timeB.adicionarEmpate();
            }

            // Registra os gols
            timeA.registrarGols(golsTimeA, golsTimeB);
            timeB.registrarGols(golsTimeB, golsTimeA);
        }
    }

    // Converte a partida para JSON
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("timeA", timeA.getNome());
        json.put("timeB", timeB.getNome());
        json.put("golsTimeA", golsTimeA);
        json.put("golsTimeB", golsTimeB);
        json.put("realizada", realizada);
        return json;
    }

    @Override
    public String toString() {
        return timeA.getNome() + " " + golsTimeA + " x " + golsTimeB + " " + timeB.getNome() + " - " + (realizada ? "Finalizada" : "Pendente");
    }
}
