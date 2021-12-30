import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import letscode.impressora.ImpressoraTabuleiro;

public class BatalhaNavalApplication {

    public final static char TIRO_CERTO = '*';
    public final static char TIRO_ERRADO = '-';
    public final static char AGUA = ' ';

    public static int[][] tabuleiro = new int[10][10];

    public static void main(String[] args) {
        System.out.println("Jogo batalha naval esta sendo iniciado...");

        boolean acabouJogo = false;
        int tentativas = 0;
        int acertos = 0;
        int erros = 0;
        int[] tiro = new int[2];
        int NUMERO_SUBMARINOS = 10;
        int COORDENADAS_SUBMARINOS = 2;
        int[][] submarinos = new int[NUMERO_SUBMARINOS][COORDENADAS_SUBMARINOS];

        inicializaTabuleiro(tabuleiro);
        ImpressoraTabuleiro.imprimirMsgInicial();
        posicionarSubmarinos(submarinos);
        System.out.println("Submarinos posicionados com sucesso!");
        ImpressoraTabuleiro.mostrarTabuleiro(tabuleiro);

        // Loop principal do jogo. Nao acaba nunca pois o contador de acertos nao esta
        // funcionando
        do {
            darTiro(tiro, tentativas);
            verificarTiro(tiro, submarinos, acertos, erros);
            alterarTabuleiro(tiro, submarinos, tabuleiro, acertos, erros);
            ImpressoraTabuleiro.mostrarTabuleiro(tabuleiro);
        } while (acertos < NUMERO_SUBMARINOS);
    }

    public static int[][] posicionarSubmarinos(int[][] submarinos) {
        int NUMERO_LINHAS = 10;
        int NUMERO_COLUNAS = 10;
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

    public static void darTiro(int[] tiro, int tentativas) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a linha do tiro: ");
        tiro[0] = scanner.nextInt();

        System.out.println("Digite a coluna do tiro: ");
        tiro[1] = scanner.nextInt();
        System.out.println("Tiro disparado!");
        tentativas++;
    }

    // TODO: O if das funcoes imprimirMsgAcertos e imprimirMsgErros nao esta
    // funcionando direito. Contador de erros e acertos nao estao functionando

    public static void imprimirMsgAcertos(int[] tiro, int acertos) {
        acertos++;
        System.out.printf("Você acertou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
        if (acertos == 1) {
            System.out.printf("Você acertou %d submarino!\n", acertos);
        } else {
            System.out.printf("Você acertou %d submarinos!\n", acertos);
        }
    }

    public static void imprimirMsgErros(int[] tiro, int erros) {
        erros++;
        System.out.printf("Você errou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
        if (erros == 1) {
            System.out.printf("Você errou %d vez!\n", erros);
        } else {
            System.out.printf("Você errou %d vezes!\n", erros);
        }
    }

    public static boolean verificarTiro(int[] tiro, int[][] submarinos, int acertos, int erros) {
        for (int submarino = 0; submarino < submarinos.length; submarino++) {
            if (tiro[0] == submarinos[submarino][0] && tiro[1] == submarinos[submarino][1]) {
                imprimirMsgAcertos(tiro, acertos);
                return true;
            }
        }
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

    public static void inicializaTabuleiro(int[][] tabuleiro) {
        for (int linha = 0; linha < tabuleiro.length; linha++)
            for (int coluna = 0; coluna < tabuleiro.length; coluna++)
                tabuleiro[linha][coluna] = 0;
    }
}
