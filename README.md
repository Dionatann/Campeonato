# Campeonato
Resumo do Escopo do Projeto

Objetivo: Desenvolver um aplicativo de linha de comando (CLI) em Java para gerenciar um campeonato de 12 rodadas com 6 times. Cada time enfrentará os demais de forma equilibrada. O projeto tem como objetivo praticar técnicas de Orientação a Objetos (OO), incluindo herança, polimorfismo, serialização e interfaces em Java.

Possíveis Funcionalidades:

    Gerenciamento de Times: Adicionar, editar, remover e listar times participantes.
    Agendamento de Partidas: Gerar automaticamente o cronograma das 12 rodadas, garantindo que cada time enfrente os demais de forma justa.
    Registro de Resultados: Inserir e atualizar os resultados das partidas após cada rodada.
    Classificação: Exibir a classificação atualizada dos times com base nos resultados.
    Persistência de Dados: Salvar e carregar os dados do campeonato utilizando serialização.
    Interação via CLI: Fornecer um menu de opções amigável para a interação com o usuário.

Sugestões de Menus de Opções

Menu Principal
=== Campeonato de Futebol ===
1. Gerenciar Times
2. Agendar Partidas
3. Registrar Resultados
4. Visualizar Classificação
5. Salvar Dados
6. Carregar Dados
7. Sair

Menu Gerenciar Times
--- Gerenciar Times ---
1. Adicionar Novo Time
2. Editar Time
3. Remover Time
4. Listar Todos os Times
5. Voltar ao Menu Principal

Menu Agendar Partidas
--- Agendar Partidas ---
1. Gerar Cronograma Automático
2. Visualizar Cronograma Atual
3. Voltar ao Menu Principal

Menu Registrar Resultados
--- Registrar Resultados ---
1. Inserir Resultado de Partida
2. Atualizar Resultado de Partida
3. Voltar ao Menu Principal

Menu Visualizar Classificação
--- Classificação ---
1. Exibir Classificação Atual
2. Voltar ao Menu Principal

// ----------------------------------------- COMPILAÇÃO ---------------------------------------------------

cmd (compliar todas as classes): javac campeonato/*.java Main.java     
cmd (executar o programa): java Main
