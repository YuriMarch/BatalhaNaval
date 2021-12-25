import java.util.Arrays;
import java.util.Random;

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
        int NUMERO_SUBMARINOS = 5;
        int COORDENADAS_SUBMARINOS = 2;
        int[][] submarinos = new int[NUMERO_SUBMARINOS][COORDENADAS_SUBMARINOS];

        ImpressoraTabuleiro.inicializaTabuleiro(tabuleiro);

        // TODO: funcao posicionarSubmarinos nao esta funcionando direito eu acho
        posicionarSubmarinos(submarinos);
        System.out.println("Submarinos posicionados com sucesso!2");
        System.out.println(Arrays.deepToString(posicionarSubmarinos(submarinos)));

    }

    public static int[][] posicionarSubmarinos(int[][] submarinos) {
        Random numeroAleatorio = new Random();

        for (int i = 0; i < submarinos.length; i++) {
            submarinos[i][0] = numeroAleatorio.nextInt(10); // Pegar linha do submarino aleatoriamente
            submarinos[i][1] = numeroAleatorio.nextInt(10); // Pegar coluna do submarino aleatoriamente

            // TODO: Verificar se o for loop abaixo funciona corretamente
            // Verificar se a posicao do submarino ja esta ocupada. Nao sei se essa parte ta
            // funcionando direito. Nao sei verificar kkkk
            for (int anterior = 1; anterior < submarinos.length; anterior++) {
                if (submarinos[i][0] == submarinos[anterior][0] && submarinos[i][1] == submarinos[anterior][1]) {
                    i--;
                }
            }
        }
        return submarinos;
    }

}
