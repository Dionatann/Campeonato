package campeonato;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo gerenciamento de times.
 */
public class GerenciadorDeTimes {

    // Lista que vai armazenar os times
    private List<Time> times;

    // Construtor da classe
    public GerenciadorDeTimes() {
        times = new ArrayList<>();
    }

    public void addTime(String nome) {
        times.add(new Time(nome));
    }

    public void removerTime(String nome) {
        times.removeIf(time -> time.getNome().equals(nome));
    }

    // Retorna a lista com os times
    public List<Time> listarTimes() {
        return times;
    }

    // Retorna se o time está na lista ou não pelo parâmetro do nome do time
    public Time buscarTimePorNome(String nome) {
        for (Time time : times) {
            if (time.getNome().equals(nome)) {
                return time;
            }
        }
        return null;
    }

    // Método para salvar a lista de times em um arquivo JSON
    public void salvarTimes(String caminhoArquivo) {
        JSONArray jsonArray = new JSONArray();
        for (Time time : times) {
            jsonArray.put(time.toJSON());  // Converte cada time para JSON e adiciona ao array
        }

        try (FileWriter file = new FileWriter(caminhoArquivo)) {
            file.write(jsonArray.toString());
            System.out.println("Times salvos com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os times: " + e.getMessage());
        }
    }

    // Método para carregar a lista de times de um arquivo JSON
    public void carregarTimes(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
            times.clear();  // Limpa a lista atual antes de carregar

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                times.add(Time.fromJSON(jsonObject));  // Converte cada JSON para uma instância de Time
            }
            System.out.println("Times carregados com sucesso de " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar os times: " + e.getMessage());
        }
    }
}
