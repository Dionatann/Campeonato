package campeonato;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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

    /**
     * Adiciona um time à lista de times.
     *
     * @param nome o nome do time a ser adicionado
     */
    public void addTime(String nome) {
        times.add(new Time(nome));
    }

    /**
     * Remove um time da lista de times pelo nome.
     *
     * @param nome o nome do time a ser removido
     */
    public void removerTime(String nome) {
        times.removeIf(time -> time.getNome().equals(nome));
    }

    /**
     * Lista todos os times.
     *
     * @return a lista de times
     */
    public List<Time> listarTimes() {
        return times;
    }

    /**
     * Busca um time pelo nome.
     *
     * @param nome o nome do time a ser buscado
     * @return o time encontrado ou null se não encontrado
     */
    public Time buscarTimePorNome(String nome) {
        for (Time time : times) {
            if (time.getNome().equals(nome)) {
                return time;
            }
        }
        return null;
    }

    /**
     * Salva a lista de times em um arquivo JSON.
     *
     * @param caminhoArquivo o caminho do arquivo onde os times serão salvos
     */
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

    /**
     * Carrega a lista de times de um arquivo JSON.
     *
     * @param caminhoArquivo o caminho do arquivo de onde os times serão carregados
     */
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
// mvn javadoc:javadoc pra gerar o arquivo do javadoc