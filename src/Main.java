import campeonato.Campeonato;
import java.util.Scanner;

/**
 * Classe principal que interage com o usuário via CLI.
 */
public class Main {
    // Instancia a classe Campeonato
    private static Campeonato campeonato = new Campeonato();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        // Estrutura do while para mostrar as opções
        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa a entrada
            switch (opcao) {
                case 1:
                    gerenciarTimes();
                    break;
                case 2:
                    agendarPartidas();
                    break;
                case 3:
                    registrarResultados();
                    break;
                case 4:
                    visualizarClassificacao();
                    break;
                case 5:
                    salvarDados();
                    break;
                case 6:
                    carregarDados();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("=== Campeonato de Futebol ===");
        System.out.println("1. Gerenciar Times");
        System.out.println("2. Agendar Partidas");
        System.out.println("3. Registrar Resultados");
        System.out.println("4. Visualizar Classificação");
        System.out.println("5. Salvar Dados");
        System.out.println("6. Carregar Dados");
        System.out.println("7. Sair");
    }

    // Menu gerenciarTimes
    private static void gerenciarTimes() {
        System.out.println("--- Gerenciar Times ---");
        System.out.println("1. Adicionar Novo Time");
        System.out.println("2. Remover Time");
        System.out.println("3. Listar Todos os Times");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa a entrada

        switch (opcao) {
            case 1:
                System.out.print("Nome do Time: ");
                String nome = scanner.nextLine();
                campeonato.addTime(nome);
                break;
            case 2:
                System.out.print("Nome do Time a Remover: ");
                nome = scanner.nextLine();
                campeonato.getGerenciadorDeTimes().removerTime(nome);
                break;
            case 3:
                campeonato.listarTimes();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void agendarPartidas() {
        campeonato.gerarCronograma();
        campeonato.listarPartidas();
    }

    private static void registrarResultados() {
        campeonato.listarPartidas();
        System.out.print("Número da Partida: ");
        int index = scanner.nextInt() - 1;
        System.out.print("Gols do Time A: ");
        int golsA = scanner.nextInt();
        System.out.print("Gols do Time B: ");
        int golsB = scanner.nextInt();
        campeonato.registrarResultado(index, golsA, golsB);
    }

    private static void visualizarClassificacao() {
        campeonato.listarTimes();
    }

    private static void salvarDados() {
        String caminhoTimes = "times.json";
        String caminhoPartidas = "partidas.json";
        
        campeonato.salvarDados(caminhoTimes, caminhoPartidas);
    }

    private static void carregarDados() {
        String caminhoTimes = "times.json";
        String caminhoPartidas = "partidas.json";
        
        campeonato.carregarDados(caminhoTimes, caminhoPartidas);
    }
}
