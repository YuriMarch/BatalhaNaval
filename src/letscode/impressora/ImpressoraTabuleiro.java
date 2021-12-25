package letscode.impressora;

public class ImpressoraTabuleiro {

  final static String MENSAGEM_INICIAL = "Tabuleiro iniciado...";

  public static void imprimirMsgInicial() {
    System.out.println(MENSAGEM_INICIAL);
    System.out.println();
  }

  public static void imprimirTabuleiro(int[][] tabuleiro) {
    System.out.println("---------------------------------------------");
    System.out.println("                 JOGADOR 1                ");
    System.out.println("---------------------------------------------");
    System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
    for (int i = 0; i < tabuleiro.length; i++) {
      System.out.printf("| %s |", i);
      for (int j = 0; j < tabuleiro[i].length; j++) {
        System.out.print(" - |");
      }
      System.out.println();
    }
  }
}
