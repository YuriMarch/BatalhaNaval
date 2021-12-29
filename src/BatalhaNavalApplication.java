import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import letscode.impressora.ImpressoraTabuleiro;

public class BatalhaNavalApplication {

    public final static char TIRO_CERTO = '*';
    public final static char TIRO_ERRADO = '-';

    public static int[][] tabuleiro = new int[10][10];

    public static void main(String[] args) {
        System.out.println("Jogo batalha naval esta sendo iniciado...");

        ImpressoraTabuleiro.imprimirMsgInicial();

        boolean isGameOver = false;
        int[] tiro = new int[2];
        int jogadorAtual = 1;
        int NUMERO_SUBMARINOS = 10;
        int COORDENADAS_SUBMARINOS = 2;
        int[][] submarinos = new int[NUMERO_SUBMARINOS][COORDENADAS_SUBMARINOS];

        inicializaTabuleiro(tabuleiro);
        mostrarTabuleiro(tabuleiro);
        // ImpressoraTabuleiro.inicializarTabuleiro(tabuleiro);

        posicionarSubmarinos(submarinos);
        System.out.println("Submarinos posicionados com sucesso!");

        for (int i = 0; i < 3; i++) {
            mostrarTabuleiro(tabuleiro);
            darTiro(tiro);
            verificarTiro(tiro, submarinos);
            alterarTabuleiro(tiro, submarinos, tabuleiro);
        }

        // Teste das funcoes verificarTiro e darTiro
        // do {
        // verificarTiro(darTiro(), submarinos);
        // atualizarTabuleiro(tabuleiro, submarinos);
        // } while (isGameOver == false);
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

            System.out.println(
                    "Submarino " + (numeroSubmarino + 1) + " posicionado na linha " + submarinos[numeroSubmarino][0]
                            + " e coluna "
                            + submarinos[numeroSubmarino][1]);
        }
        return submarinos;
    }

    public static void darTiro(int[] tiro) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a linha do tiro: ");
        tiro[0] = scanner.nextInt();

        System.out.println("Digite a coluna do tiro: ");
        tiro[1] = scanner.nextInt();
        System.out.println("Tiro disparado!");
    }

    public static boolean verificarTiro(int[] tiro, int[][] submarinos) {
        for (int submarino = 0; submarino < submarinos.length; submarino++) {
            if (tiro[0] == submarinos[submarino][0] && tiro[1] == submarinos[submarino][1]) {
                System.out.printf("Você acertou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
                return true;
            }
        }
        System.out.printf("Você errou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
        return false;
    }

    public static void alterarTabuleiro(int[] tiro, int[][] submarinos, int[][] tabuleiro) {
        if (verificarTiro(tiro, submarinos) == true) {
            tabuleiro[tiro[0]][tiro[1]] = 1;
        } else {
            tabuleiro[tiro[0]][tiro[1]] = -1;
        }
    }

    public static void atualizarTabuleiro(int[][] tabuleiro, int[][] submarinos) {
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
                if (tabuleiro[linha][coluna] == 0) {
                    System.out.print("-");
                } else if (tabuleiro[linha][coluna] == 1) {
                    System.out.print("*");
                } else if (tabuleiro[linha][coluna] == -1) {
                    System.out.print("X");
                }
            }
        }
    }

    public static void inicializaTabuleiro(int[][] tabuleiro) {
        for (int linha = 0; linha < tabuleiro.length; linha++)
            for (int coluna = 0; coluna < tabuleiro.length; coluna++)
                tabuleiro[linha][coluna] = 0;
    }

    public static void mostrarTabuleiro(int[][] tabuleiro) {
        System.out.println("---------------------------------------------");
        System.out.println("                 JOGADOR 1                ");
        System.out.println("---------------------------------------------");
        System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
        System.out.println("---------------------------------------------");
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            System.out.printf("| %s |", linha);
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
                if (tabuleiro[linha][coluna] == 0) {
                    System.out.print("   |");
                } else if (tabuleiro[linha][coluna] == 1) {
                    System.out.print(" * |");
                } else if (tabuleiro[linha][coluna] == -1) {
                    System.out.print(" - |");
                }
            }
            System.out.println();
            System.out.println("---------------------------------------------");
        }
    }

}
