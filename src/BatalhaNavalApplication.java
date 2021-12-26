import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

import letscode.impressora.ImpressoraTabuleiro;

public class BatalhaNavalApplication {

    public final String tiroCerto = "*";
    public final String tiroErrado = "-";
    public boolean isGameOver = false;
    public static int[][] tabuleiro = new int[10][10];

    public static void main(String[] args) {
        System.out.println("Jogo batalha naval esta sendo iniciado...");

        ImpressoraTabuleiro.imprimirMsgInicial();

        int jogadorAtual = 1;
        int NUMERO_SUBMARINOS = 10;
        int COORDENADAS_SUBMARINOS = 2;
        int[][] submarinos = new int[NUMERO_SUBMARINOS][COORDENADAS_SUBMARINOS];

        ImpressoraTabuleiro.inicializaTabuleiro(tabuleiro);

        posicionarSubmarinos(submarinos);
        System.out.println("Submarinos posicionados com sucesso!");

    }

    public static void posicionarSubmarinos(int[][] submarinos) {
        int NUMERO_LINHAS = 10;
        int NUMERO_COLUNAS = 10;
        Random numeroAleatorio = new Random();

        for (int numeroSubmarino = 0; numeroSubmarino < submarinos.length; numeroSubmarino++) {
            submarinos[numeroSubmarino][0] = numeroAleatorio.nextInt(NUMERO_LINHAS);
            submarinos[numeroSubmarino][1] = numeroAleatorio.nextInt(NUMERO_COLUNAS);

            // Metodo de verificar se ja existe um submarino na posicao. Se houver, uma nova
            // posicao e escolhida. Ha ainda a possibilidade de um submarino colidir com
            // outro. Mas e baixa pois so tem 10 submarinos.
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
    }

    // TODO: testar e implementar esta funcao
    public static void darTiro(int[] tiro) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a linha do tiro: ");
        tiro[0] = scanner.nextInt();

        System.out.println("Digite a coluna do tiro: ");
        tiro[1] = scanner.nextInt();
    }
}
