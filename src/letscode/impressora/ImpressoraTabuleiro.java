package letscode.impressora;

public class ImpressoraTabuleiro {

  final static String MENSAGEM_INICIAL = "Tabuleiro iniciado...";

  public static void imprimirMsgInicial() {
    System.out.println(MENSAGEM_INICIAL);
    System.out.println();
  }

  public static void inicializaTabuleiro(int[][] tabuleiro) {
    System.out.println("---------------------------------------------");
    System.out.println("                 JOGADOR 1                ");
    System.out.println("---------------------------------------------");
    System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
    System.out.println("---------------------------------------------");
    for (int i = 0; i < tabuleiro.length; i++) {
      // Troquei o eixo do tabuleiro pra botar os numeros dentro
      // do for loop. Consegui mais facil assim...

      System.out.printf("| %s |", i);
      for (int j = 0; j < tabuleiro[i].length; j++) {
        System.out.print("   |");
        tabuleiro[i][j] = 0;
      }
      System.out.println();
      System.out.println("---------------------------------------------");
    }
  }
}
