import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDePartidas {
    private List<Partida> partidas;

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

    // Método para salvar partidas em JSON
    public void salvarPartidasEmJson(String caminhoArquivo) {
        JSONArray jsonArray = new JSONArray();
        for (Partida partida : partidas) {
            jsonArray.put(partida.toJSON());
        }

        try (FileWriter file = new FileWriter(caminhoArquivo)) {
            file.write(jsonArray.toString(4));  // 4 é a indentação para leitura
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar partidas de JSON
    public void carregarPartidasDeJson(String caminhoArquivo) {
        partidas.clear();
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            JSONArray jsonArray = new JSONArray(new String(reader.readAllBytes()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Time timeA = new Time(json.getString("timeA"));  // Supondo que Time tenha esse construtor
                Time timeB = new Time(json.getString("timeB"));
                Partida partida = new Partida(timeA, timeB);
                if (json.getBoolean("realizada")) {
                    partida.addResultado(json.getInt("golsTimeA"), json.getInt("golsTimeB"));
                }
                partidas.add(partida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
