package campeonato;

import org.json.JSONObject;
import java.io.Serializable;

/**
 * Classe que representa um time no campeonato.
 * Implementa Serializable para permitir a persistência dos dados.
 */
public class Time implements Serializable {

    private String nome;
    private int pontos, vitorias, empates, derrotas, golsPro, golsContra;

    // Construtor da classe 
    public Time(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.derrotas = 0; 
        this.empates = 0;
        this.vitorias = 0;
        this.golsContra = 0;
        this.golsPro = 0;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getGolsPro() {
        return golsPro;
    }

    public int getGolsContra() {
        return golsContra;
    }

    public void adicionarVitoria() {
        vitorias++;
        pontos += 3;
    }

    public void adicionarEmpate() {
        empates++;
        pontos++;
    }

    public void adicionarDerrota() {
        derrotas++;
    }

    public void registrarGols(int marcados, int sofridos) {
        this.golsPro += marcados;
        this.golsContra += sofridos;
    }

    @Override // herdada do Object
    public String toString() {
        return nome + " - " + pontos + " pontos";
    }

    // Método para converter a instância de Time em um JSONObject
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("nome", nome);
        json.put("pontos", pontos);
        json.put("vitorias", vitorias);
        json.put("empates", empates);
        json.put("derrotas", derrotas);
        json.put("golsPro", golsPro);
        json.put("golsContra", golsContra);
        return json;
    }

    // Método estático para criar uma instância de Time a partir de um JSONObject
    public static Time fromJSON(JSONObject json) {
        Time time = new Time(json.getString("nome"));
        time.pontos = json.getInt("pontos");
        time.vitorias = json.getInt("vitorias");
        time.empates = json.getInt("empates");
        time.derrotas = json.getInt("derrotas");
        time.golsPro = json.getInt("golsPro");
        time.golsContra = json.getInt("golsContra");
        return time;
    }
}
