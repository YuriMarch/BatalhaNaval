package letscode.impressora;

public class ImpressoraMensagens {

  final static String MENSAGEM_INICIAL = "Tabuleiro iniciado...";

  public static void imprimirMsgInicial() {
    System.out.println(MENSAGEM_INICIAL);
  }

  public static void imprimirMsgAcertos(int[] tiro, int acertos) {
    System.out.printf("Você acertou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
    if (acertos == 1) {
      System.out.printf("Você acertou %d submarino!\n", acertos);
    } else {
      System.out.printf("Você acertou %d submarinos!\n", acertos);
    }
  }

  public static void imprimirMsgErros(int[] tiro, int erros) {
    System.out.printf("Você errou o tiro nas coordenadas (%d, %d)!\n", tiro[0], tiro[1]);
    if (erros == 1) {
      System.out.printf("Você errou %d vez!\n", erros);
    } else {
      System.out.printf("Você errou %d vezes!\n", erros);
    }
  }

}