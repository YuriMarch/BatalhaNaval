package letscode.impressora;

public class ImpressoraTabuleiro {

  final static String MENSAGEM_INICIAL = "Tabuleiro iniciado...";

  private static final char AGUA = ' ';
  private static final char TIRO_CERTO = '*';
  private static final char TIRO_ERRADO = '-';

  public static void imprimirMsgInicial() {
    System.out.println(MENSAGEM_INICIAL);
  }

  public static void imprimirCabecalho() {
    System.out.println("---------------------------------------------");
    System.out.println("                 JOGADOR 1                ");
    System.out.println("---------------------------------------------");
    System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
    System.out.println("---------------------------------------------");
  }

  public static void mostrarTabuleiro(int[][] tabuleiro) {
    imprimirCabecalho();
    for (int linha = 0; linha < tabuleiro.length; linha++) {
      System.out.printf("| %s |", linha);
      for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
        if (tabuleiro[linha][coluna] == 0) {
          System.out.printf(" %s |", AGUA);
        } else if (tabuleiro[linha][coluna] == 1) {
          System.out.printf(" %s |", TIRO_CERTO);
        } else if (tabuleiro[linha][coluna] == -1) {
          System.out.printf(" %s |", TIRO_ERRADO);
        }
      }
      System.out.println();
      System.out.println("---------------------------------------------");
    }
  }
}
