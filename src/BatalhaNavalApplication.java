import java.util.Random;

import letscode.impressora.ImpressoraTabuleiro;
import letscode.leitor.LeitorInput;

public class BatalhaNavalApplication {

    public final static int NUMERO_LINHAS = 10;
    public final static int NUMERO_COLUNAS = 10;
    public static int[][] tabuleiro = new int[NUMERO_LINHAS][NUMERO_COLUNAS];
    public static int tentativas = 0;
    public static int acertos = 0;
    public static int erros = 0;

    public static void main(String[] args) {
        System.out.println("Jogo batalha naval esta sendo iniciado...");

        boolean acabouJogo = false;
        int[] tiro = new int[2];
        int NUMERO_SUBMARINOS = 10;
        int COORDENADAS_SUBMARINOS = 2;
        int[][] submarinos = new int[NUMERO_SUBMARINOS][COORDENADAS_SUBMARINOS];

        inicializarTabuleiro(tabuleiro);
        ImpressoraTabuleiro.imprimirMsgInicial();
        ImpressoraTabuleiro.mostrarTabuleiro(tabuleiro);
        posicionarSubmarinosAleatoriamente(submarinos);
        // posicionarSubmarinosManual(submarinos);
        System.out.println("Submarinos posicionados com sucesso!");
        ImpressoraTabuleiro.mostrarTabuleiro(tabuleiro);

        // Loop principal do jogo. Nao acaba nunca pois o contador de acertos nao esta
        // funcionando
        do {
            darTiroManual(tiro, tentativas);
            verificarTiro(tiro, submarinos, acertos, erros);
            alterarTabuleiro(tiro, submarinos, tabuleiro, acertos, erros);
            ImpressoraTabuleiro.mostrarTabuleiro(tabuleiro);
        } while (verificarFimDoJogo(acertos, NUMERO_SUBMARINOS, acabouJogo) == false);
    }

    public static int[][] posicionarSubmarinosManual(int[][] submarinos) {
        String errorMsg = "Entrada inválida. ";

        for (int numeroSubmarino = 0; numeroSubmarino < submarinos.length; numeroSubmarino++) {
            String inputLinhaMsg = "Digite a linha do submarino " + (numeroSubmarino + 1) + " (0 a "
                    + (NUMERO_LINHAS - 1) + "): ";
            String inputColunaMsg = "Digite a linha do submarino " + (numeroSubmarino + 1) + " (0 a "
                    + (NUMERO_COLUNAS - 1) + "): ";
            submarinos[numeroSubmarino][0] = LeitorInput.lerInteiro(inputLinhaMsg, errorMsg, 0,
                    NUMERO_LINHAS - 1);
            submarinos[numeroSubmarino][1] = LeitorInput.lerInteiro(inputColunaMsg, errorMsg,
                    0, NUMERO_COLUNAS - 1);

            // Print das coordenadas dos submarinos para fazer testes
            System.out.println(
                    "Submarino " + (numeroSubmarino + 1) + " posicionado na linha " + submarinos[numeroSubmarino][0]
                            + " e coluna "
                            + submarinos[numeroSubmarino][1]);
        }

        return submarinos;
    }

    public static int[][] posicionarSubmarinosAleatoriamente(int[][] submarinos) {
        Random numeroAleatorio = new Random();

        for (int numeroSubmarino = 0; numeroSubmarino < submarinos.length; numeroSubmarino++) {
            submarinos[numeroSubmarino][0] = numeroAleatorio.nextInt(NUMERO_LINHAS);
            submarinos[numeroSubmarino][1] = numeroAleatorio.nextInt(NUMERO_COLUNAS);

            // Metodo de verificar se ja existe um submarino na posicao. Se houver, uma nova
            // posicao e escolhida. Ha ainda a possibilidade de um submarino colidir com
            // outro. Mas e baixa po is so tem 10 submarinos.
            for (int i = 0; i < submarinos.length; i++) {
                if (i != numeroSubmarino) {
                    if (submarinos[numeroSubmarino][0] == submarinos[i][0]
                            && submarinos[numeroSubmarino][1] == submarinos[i][1]) {
                        submarinos[numeroSubmarino][0] = numeroAleatorio.nextInt(NUMERO_LINHAS);
                        submarinos[numeroSubmarino][1] = numeroAleatorio.nextInt(NUMERO_COLUNAS);
                    }
                }
            }

            // Print das coordenadas dos submarinos para fazer testes
            System.out.println(
                    "Submarino " + (numeroSubmarino + 1) + " posicionado na linha " + submarinos[numeroSubmarino][0]
                            + " e coluna "
                            + submarinos[numeroSubmarino][1]);
        }
        return submarinos;
    }

    public static void darTiroManual(int[] tiro, int tentativas) {
        String inputLinhaMsg = "Digite a linha do tiro (0 a " + (NUMERO_LINHAS - 1) + "): ";
        String inputColunaMsg = "Digite a coluna do tiro (0 a " + (NUMERO_COLUNAS - 1) + "): ";
        String errorMsg = "Entrada inválida. ";

        tiro[0] = LeitorInput.lerInteiro(inputLinhaMsg, errorMsg, 0, NUMERO_LINHAS - 1);
        tiro[1] = LeitorInput.lerInteiro(inputColunaMsg, errorMsg, 0, NUMERO_COLUNAS - 1);

        tentativas++; // contador de tentativas nao esta funcionando
    }

    public static void darTiroAleatoriamente(int[] tiro, int tentativas) {
        Random numeroAleatorio = new Random();

        tiro[0] = numeroAleatorio.nextInt(NUMERO_LINHAS);
        tiro[1] = numeroAleatorio.nextInt(NUMERO_COLUNAS);
        tentativas++; // contador de tentativas nao esta funcionando
    }

    // TODO: O if das funcoes imprimirMsgAcertos e imprimirMsgErros nao esta
    // funcionando direito. Contador de erros e acertos nao estao functionando

    public static void imprimirMsgAcertos(int[] tiro, int acertos) {
        System.out.printf("Você acertou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
        // if (acertos == 1) {
        // System.out.printf("Você acertou %d submarino!\n", acertos);
        // } else {
        // System.out.printf("Você acertou %d submarinos!\n", acertos);
        // }

        System.out.printf("Você acertou %s submarino(s)!\n", acertos);
    }

    public static void imprimirMsgErros(int[] tiro, int erros) {
        System.out.printf("Você errou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
        // if (erros == 1) {
        // System.out.printf("Você errou %d vez!\n", erros);
        // } else {
        // System.out.printf("Você errou %d vezes!\n", erros);
        // }
        System.out.printf("Você errou %s vez(es)!\n", erros);
    }

    public static boolean verificarTiro(int[] tiro, int[][] submarinos, int acertos, int erros) {
        for (int submarino = 0; submarino < submarinos.length; submarino++) {
            if (tiro[0] == submarinos[submarino][0] && tiro[1] == submarinos[submarino][1]) {
                acertos++;
                imprimirMsgAcertos(tiro, acertos);
                return true;
            }
        }
        erros++;
        imprimirMsgErros(tiro, erros);
        return false;
    }

    public static void alterarTabuleiro(int[] tiro, int[][] submarinos, int[][] tabuleiro, int acertos, int erros) {
        if (verificarTiro(tiro, submarinos, acertos, erros) == true) {
            tabuleiro[tiro[0]][tiro[1]] = 1; // 1 significa que o tiro acertou um submarino
        } else {
            tabuleiro[tiro[0]][tiro[1]] = -1; // -1 significa que o tiro acertou a agua
        }
    }

    public static void inicializarTabuleiro(int[][] tabuleiro) {
        for (int linha = 0; linha < tabuleiro.length; linha++)
            for (int coluna = 0; coluna < tabuleiro.length; coluna++)
                tabuleiro[linha][coluna] = 0;
    }

    public static boolean verificarFimDoJogo(int acertos, int NUMERO_SUBMARINOS, boolean acabouJogo) {
        if (acertos == NUMERO_SUBMARINOS) {
            System.out.println("Parabens! Voce acertou todos os submarinos!");
            acabouJogo = true;
        }
        return acabouJogo;
    }
}

// TODO:
// - Consertar os contadores acertos, erros, tentativas
// - Criar classe para o jogador e o computador
// - Criar metodo de dar tiro aleatoriamente para o computador --> FEITO
// - Verificar se a funcao verificarFimDoJogo ta funcionado direito
// - Tentar consertar o erro do scanner.close()