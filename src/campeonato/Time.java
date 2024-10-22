package campeonato;
import java.io.Serializable;

/**
 * Classe que representa um time no campeonato.
 * Implementa Serializable para permitir a persistência dos dados.
 */

 public class Time implements Serializable{

    private String nome;
    private int pontos, vitorias, empates, derrotas, golsPro, golsContra;

    // construtor da classe 
    public Time(String nome){
        this.nome = nome;
        this.pontos = 0;
        this.derrotas = 0; 
        this.empates = 0;
        this.vitorias = 0;
        this.golsContra = 0;
        this.golsPro = 0;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarVitoria() {
        vitorias++; pontos += 3;
    }

    public void adicionarEmpate() {
        empates++; pontos++;
    }

    public void adicionarDerrota() {
        derrotas++;
    }

    public void registrarGols(int marcados, int sofridos) {
        this.golsPro += marcados; this.golsContra += sofridos;
    }

    @Override // herdada do Object
    public String toString() {
        return nome + " - " + pontos + " pontos";
    }
}
