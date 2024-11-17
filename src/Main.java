package campeonato;
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
                    System.out.print("Quantos times deseja gerar? ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    gerarTimesAleatorios(quantidade);
                    break;
                case 8:
                    registrarResultadosAutomaticos();
                    break;
                case 9:
                    aplicarFiltro();
                    break;
                case 10:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 10);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("=== Campeonato de Futebol ===");
        System.out.println("1. Gerenciar Times");
        System.out.println("2. Agendar Partidas");
        System.out.println("3. Registrar Resultados");
        System.out.println("4. Visualizar Classificação");
        System.out.println("5. Salvar Dados");
        System.out.println("6. Carregar Dados");
        System.out.println("7. Gerar Times Aleatórios");
        System.out.println("8. Registrar Resultados Automáticos");
        System.out.println("9. Aplicar Filtros");
        System.out.println("10. Sair");
        System.out.print("Escolha uma opção: ");
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
        var times = campeonato.getGerenciadorDeTimes().listarTimes();
        if (times.isEmpty()) {
            System.out.println("Nenhum time cadastrado.");
        } else {
            System.out.println("--- Classificação dos Times ---");
            times.forEach(System.out::println);
        }
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

    private static void gerarTimesAleatorios(int quantidade) {
        String[] nomesBase = {"Leões", "Tigres", "Falcões", "Águias", "Lobos", "Panteras", "Dragões"};
        for (int i = 0; i < quantidade; i++) {
            String nome = nomesBase[i % nomesBase.length] + " " + (i + 1);
            campeonato.addTime(nome);
        }
        System.out.println(quantidade + " times gerados automaticamente.");
    }
    
    private static void registrarResultadosAutomaticos() {
        campeonato.getGerenciadorDePartidas().registrarResultadosAleatorios();
        System.out.println("Resultados automáticos registrados para todas as partidas pendentes.");
    }

    private static void aplicarFiltro() {
        System.out.println("--- Aplicar Filtros ---");
        System.out.println("1. Partidas Realizadas");
        System.out.println("2. Partidas de um Time");
        System.out.println("3. Times com Saldo de Gols Positivo");
        System.out.println("4. Times com Menos de 5 Pontos");
        System.out.println("5. Voltar");
        int opcao = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcao) {
            case 1:
                var realizadas = campeonato.getGerenciadorDePartidas().listarPartidasRealizadas();
                if (realizadas.isEmpty()) {
                    System.out.println("Nenhuma partida realizada.");
                } else {
                    realizadas.forEach(System.out::println);
                }
                break;
            case 2:
                System.out.print("Digite o nome do time: ");
                String nomeTime = scanner.nextLine();
                var time = campeonato.getGerenciadorDeTimes().buscarTimePorNome(nomeTime);
                if (time != null) {
                    var partidasDoTime = campeonato.getGerenciadorDePartidas().listarPartidasPorTime(time);
                    if (partidasDoTime.isEmpty()) {
                        System.out.println("Nenhuma partida encontrada para o time '" + nomeTime + "'.");
                    } else {
                        partidasDoTime.forEach(System.out::println);
                    }
                } else {
                    System.out.println("Time não encontrado.");
                }
                break;
            case 3:
                var timesPositivos = campeonato.getGerenciadorDeTimes().listarTimesComSaldoPositivo();
                if (timesPositivos.isEmpty()) {
                    System.out.println("Nenhum time com saldo de gols positivo.");
                } else {
                    timesPositivos.forEach(System.out::println);
                }
                break;
            case 4:
                var poucosPontos = campeonato.getGerenciadorDeTimes().listarTimesComMenosDeCincoPontos();
                if (poucosPontos.isEmpty()) {
                    System.out.println("Nenhum time com menos de 5 pontos.");
                } else {
                    poucosPontos.forEach(System.out::println);
                }
                break;
            case 5:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
    
    
}
