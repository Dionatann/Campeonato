package campeonato;

import java.io.*;
import java.util.List;

/**
 * Classe que representa o campeonato e suas operações principais.
 */
public class Campeonato {
    private GerenciadorDeTimes gerenciadorDeTimes;
    private GerenciadorDePartidas gerenciadorDePartidas;

    // Construtor que cria as instâncias para gerenciar as partidas e times
    public Campeonato() {
        gerenciadorDeTimes = new GerenciadorDeTimes();
        gerenciadorDePartidas = new GerenciadorDePartidas();
    }   

    // Adiciona um time ao gerenciador de times
    public void addTime(String nome) {
        gerenciadorDeTimes.addTime(nome);
    }

    // Lista times do campeonato
    public void listarTimes() {
        List<Time> times = gerenciadorDeTimes.listarTimes();
        for (Time time : times) {
            System.out.println(time);
        }
    }

    // Gera o cronograma das partidas
    public void gerarCronograma() {
        gerenciadorDePartidas.gerarCronograma(gerenciadorDeTimes.listarTimes());
    }

    public void listarPartidas() {
        List<Partida> partidas = gerenciadorDePartidas.listarPartidas();
        for (int i = 0; i < partidas.size(); i++) {
            System.out.println((i + 1) + ": " + partidas.get(i));
        }
    }

    // Registra o resultado de uma partida
    public void registrarResultado(int index, int golsTimeA, int golsTimeB) {
        gerenciadorDePartidas.addResultado(index, golsTimeA, golsTimeB);
    }

    // Salva os dados dos times e das partidas
    public void salvarDados(String caminhoTimes, String caminhoPartidas) {
        try {
            gerenciadorDeTimes.salvarTimes(caminhoTimes);
            gerenciadorDePartidas.salvarPartidas(caminhoPartidas);
            System.out.println("Dados do campeonato salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados do campeonato: " + e.getMessage());
        }
    }

    // Carrega os dados dos times e das partidas
    public void carregarDados(String caminhoTimes, String caminhoPartidas) {
        try {
            gerenciadorDeTimes.carregarTimes(caminhoTimes);
            gerenciadorDePartidas.carregarPartidas(caminhoPartidas);
            System.out.println("Dados do campeonato carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados do campeonato: " + e.getMessage());
        }
    }
}
