import campeonato.GerenciadorDeTimes;
import campeonato.Time;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GerenciadorDeTimesTest {

    private GerenciadorDeTimes gerenciador;

    @Before
    public void setUp() {
        gerenciador = new GerenciadorDeTimes();
    }

    @Test
    public void testAddTime() {
        gerenciador.addTime("Time A");
        List<Time> times = gerenciador.listarTimes();
        assertEquals(1, times.size());
        assertEquals("Time A", times.get(0).getNome());
    }

    @Test
    public void testRemoverTime() {
        gerenciador.addTime("Time A");
        gerenciador.removerTime("Time A");
        List<Time> times = gerenciador.listarTimes();
        assertEquals(0, times.size());
    }

    @Test
    public void testListarTimes() {
        gerenciador.addTime("Time A");
        gerenciador.addTime("Time B");
        List<Time> times = gerenciador.listarTimes();
        assertEquals(2, times.size());
        assertEquals("Time A", times.get(0).getNome());
        assertEquals("Time B", times.get(1).getNome());
    }

    @Test
    public void testBuscarTimePorNome() {
        gerenciador.addTime("Time A");
        Time time = gerenciador.buscarTimePorNome("Time A");
        assertNotNull(time);
        assertEquals("Time A", time.getNome());
    }

    @Test
    public void testBuscarTimePorNomeInexistente() {
        gerenciador.addTime("Time A");
        Time time = gerenciador.buscarTimePorNome("Time B");
        assertNull(time);
    }
    
    @Test
    public void testSalvarTimesECarregarTimes() {
        String caminhoArquivo = "times_test.json";
        gerenciador.addTime("Time A");
        gerenciador.addTime("Time B");
        
        // Salvar times
        gerenciador.salvarTimes(caminhoArquivo);
        
        // Criar um novo gerenciador e carregar os times salvos
        GerenciadorDeTimes novoGerenciador = new GerenciadorDeTimes();
        novoGerenciador.carregarTimes(caminhoArquivo);
        
        List<Time> times = novoGerenciador.listarTimes();
        assertEquals(2, times.size());
        assertEquals("Time A", times.get(0).getNome());
        assertEquals("Time B", times.get(1).getNome());
        
        // Remover o arquivo após o teste
        new java.io.File(caminhoArquivo).delete();
    }

    //TODO: testar no pc da uffs pra ver se funciona
}
