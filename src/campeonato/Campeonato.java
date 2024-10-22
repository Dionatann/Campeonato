package campeonato;

import java.io.*;
import java.util.List;

/**
 * Classe que representa o campeonato e suas operações principais.
 */
public class Campeonato {
    private GerenciadorDeTimes gerenciadorDeTimes;
    private GerenciadorDePartidas gerenciadorDePartidas;

    // costrutor cria as duas classes de gerenciar as partidas e times
    public Campeonato() {
        gerenciadorDeTimes = new GerenciadorDeTimes();
        gerenciadorDePartidas = new GerenciadorDePartidas();
    }   

    // Adicone um time ao gerenciador de time
    public void addTime(String nome) {
        gerenciadorDeTimes.addTime(nome);
    }

    // lista times do campeonato
    public void listarTimes() {
        List<Time> times = gerenciadorDeTimes.listarTimes();
        for (Time time : times) {
            System.out.println(time);
        }
    }

    // gera o conograma da partida
    public void gerarCronograma() {
        gerenciadorDePartidas.gerarCronograma(gerenciadorDeTimes.listarTimes());
    }

    public void listarPartidas() {
        List<Partida> partidas = gerenciadorDePartidas.listarPartidas();
        for (int i = 0; i < partidas.size(); i++) {
            System.out.println((i + 1) + ": " + partidas.get(i));
        }
    }

    public void registrarResultado(int index, int golsTimeA, int golsTimeB) {
        gerenciadorDePartidas.addResultado(index, golsTimeA, golsTimeB);
    }


    // salva os dados
    public void salvarDados(String caminho) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(this);
        }
    }

    public static Campeonato carregarDados(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (Campeonato) ois.readObject();
        }
    }
}
