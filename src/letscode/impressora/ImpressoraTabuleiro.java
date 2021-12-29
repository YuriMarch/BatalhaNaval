package letscode.impressora;

public class ImpressoraTabuleiro {

  final static String MENSAGEM_INICIAL = "Tabuleiro iniciado...";

  public static void imprimirMsgInicial() {
    System.out.println(MENSAGEM_INICIAL);
    System.out.println();
  }

  public static void inicializarTabuleiro(int[][] tabuleiro) {
    System.out.println("---------------------------------------------");
    System.out.println("                 JOGADOR 1                ");
    System.out.println("---------------------------------------------");
    System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
    System.out.println("---------------------------------------------");
    for (int linha = 0; linha < tabuleiro.length; linha++) {
      // Troquei o eixo do tabuleiro pra botar os numeros dentro
      // do for loop. Consegui mais facil assim...

      System.out.printf("| %s |", linha);
      for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
        System.out.print("   |");
        // tabuleiro[linha][coluna] = 0;
      }
      System.out.println();
      System.out.println("---------------------------------------------");
    }
  }
}
