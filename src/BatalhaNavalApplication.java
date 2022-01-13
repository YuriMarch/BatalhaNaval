import java.util.Random;

import letscode.impressora.ImpressoraTabuleiro;
import letscode.leitor.LeitorInput;

public class BatalhaNavalApplication {

    public final static int NUMERO_LINHAS = 10;
    public final static int NUMERO_COLUNAS = 10;
    public static int[][] tabuleiro = new int[NUMERO_LINHAS][NUMERO_COLUNAS];
    public static int tentativas = 0;
    public static int acertos = 0; // -1 ao inves de zero pois a variavel acertou esta incrementando o valor no
                                   // inicio do jogo
    public static int erros = 0;
    private static boolean acertou = false;

    public static void main(String[] args) {
        System.out.println("Jogo batalha naval esta sendo iniciado...");

        boolean acabouJogo = false;
        int[] tiro = new int[2];
        int NUMERO_SUBMARINOS = 2;
        int COORDENADAS_SUBMARINOS = 2;
        int[][] submarinos = new int[NUMERO_SUBMARINOS][COORDENADAS_SUBMARINOS];
        System.out.printf("Acertos: %s \n", acertos);
        System.out.printf("Erros: %s \n", erros);

        inicializarTabuleiro(tabuleiro);
        ImpressoraTabuleiro.imprimirMsgInicial();
        posicionarSubmarinosAleatoriamente(submarinos);
        // posicionarSubmarinosManual(submarinos);
        System.out.println("Submarinos posicionados com sucesso!");
        ImpressoraTabuleiro.mostrarTabuleiro(tabuleiro);

        do {
            System.out.println("Inicio darTiroManual");
            System.out.println("Acertos: " + BatalhaNavalApplication.acertos);
            System.out.println("Erros: " + BatalhaNavalApplication.erros);
            System.out.println("Tentativas: " + BatalhaNavalApplication.tentativas);
            darTiroManual(tiro, tentativas);
            System.out.println("Inicio verificarTiro");
            System.out.println("Acertos: " + BatalhaNavalApplication.acertos);
            System.out.println("Erros: " + BatalhaNavalApplication.erros);
            System.out.println("Tentativas: " + BatalhaNavalApplication.tentativas);
            verificarTiro(tiro, submarinos);
            System.out.println("Inicio alterarTabuleiro");
            System.out.println("Acertos: " + BatalhaNavalApplication.acertos);
            System.out.println("Erros: " + BatalhaNavalApplication.erros);
            System.out.println("Tentativas: " + BatalhaNavalApplication.tentativas);
            System.out.println(acertou);
            alterarTabuleiro(tiro, submarinos, tabuleiro, acertou);
            ImpressoraTabuleiro.mostrarTabuleiro(tabuleiro);
        } while (verificarFimDoJogo(BatalhaNavalApplication.acertos, NUMERO_SUBMARINOS, acabouJogo) == false);
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

        tentativas++;
    }

    public static void darTiroAleatoriamente(int[] tiro, int tentativas) {
        Random numeroAleatorio = new Random();

        tiro[0] = numeroAleatorio.nextInt(NUMERO_LINHAS);
        tiro[1] = numeroAleatorio.nextInt(NUMERO_COLUNAS);
        tentativas++;
    }

    public static void imprimirMsgAcertos(int[] tiro, int acertos) {
        System.out.printf("Você acertou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
        if (acertos == 1) {
            System.out.printf("Você acertou %d submarino!\n", BatalhaNavalApplication.acertos);
        } else {
            System.out.printf("Você acertou %d submarinos!\n", BatalhaNavalApplication.acertos);
        }
    }

    public static void imprimirMsgErros(int[] tiro, int erros) {
        System.out.printf("Você errou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
        if (erros == 1) {
            System.out.printf("Você errou %d vez!\n", BatalhaNavalApplication.erros);
        } else {
            System.out.printf("Você errou %d vezes!\n", BatalhaNavalApplication.erros);
        }
    }

    public static boolean verificarTiro(int[] tiro, int[][] submarinos) {
        for (int submarino = 0; submarino < submarinos.length; submarino++) {
            if (tiro[0] == submarinos[submarino][0] && tiro[1] == submarinos[submarino][1]) {
                BatalhaNavalApplication.acertos++;
                imprimirMsgAcertos(tiro, BatalhaNavalApplication.acertos);
                acertou = true;
                return true;
            }
        }
        BatalhaNavalApplication.erros++;
        imprimirMsgErros(tiro, BatalhaNavalApplication.erros);
        acertou = false;
        return false;
    }

    public static void alterarTabuleiro(int[] tiro, int[][] submarinos, int[][] tabuleiro, boolean acertou) {
        if (acertou) {
            System.out.println("alterarTabuleiro: acertou!");
            tabuleiro[tiro[0]][tiro[1]] = 1; // 1 significa que o tiro acertou um submarino
        } else {
            System.out.println("alterarTabuleiro: errou!");
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
// - Consertar os contadores acertos, erros, tentativas --> FEITO
// - Criar classe para o jogador e o computador
// - Criar metodo de dar tiro aleatoriamente para o computador --> FEITO
// - Verificar se a funcao verificarFimDoJogo ta funcionado direito -->FEITO
// - Tentar consertar o erro do scanner.close()