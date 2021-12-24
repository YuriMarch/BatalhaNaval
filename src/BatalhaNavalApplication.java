import letscode.impressora.ImpressoraTabuleiro;

public class BatalhaNavalApplication {
    public static void main(String[] args) {
        System.out.println("Jogo batalha naval esta sendo iniciado...");

        ImpressoraTabuleiro.imprimirMsgInicial();

        int[][] tabuleiro = new int[10][10];
        ImpressoraTabuleiro.imprimirTabuleiro(tabuleiro);
    }
}
