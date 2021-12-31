import java.util.Random;
import java.util.Scanner;

import letscode.impressora.ImpressoraTabuleiro;

public class BatalhaNavalApplication {

    public final static char TIRO_CERTO = '*';
    public final static char TIRO_ERRADO = '-';
    public final static char AGUA = ' ';
    public final static int NUMERO_LINHAS = 10;
    public final static int NUMERO_COLUNAS = 10;
    public static int[][] tabuleiro = new int[NUMERO_LINHAS][NUMERO_COLUNAS];

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
        } while (verificarFimDoJogo(acertos, NUMERO_SUBMARINOS, acabouJogo) == false);
    }

    public static int[][] posicionarSubmarinos(int[][] submarinos) {
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

    // TODO: proteger a funcao de erros:
    // - int entre 0 e 9
    // - nao pode ser string --> FEITO!
    // - 2 loops para a solucao do problema acima esta estranho...
    public static void darTiro(int[] tiro, int tentativas) {
        String strInput;
        boolean inputLinhaValido = false;
        boolean inputColunaValido = false;

        Scanner scanner = new Scanner(System.in);

        // Loop para verificar se o input do usuario e valido
        while (inputLinhaValido == false) {
            System.out.printf("Digite a linha do tiro (0 a %s): ", NUMERO_LINHAS - 1);
            strInput = scanner.nextLine();
            try {
                tiro[0] = Integer.parseInt(strInput);
                inputLinhaValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }

        while (inputColunaValido == false) {
            System.out.printf("Digite a coluna do tiro (0 a %s): ", NUMERO_COLUNAS - 1);
            strInput = scanner.nextLine();
            try {
                tiro[1] = Integer.parseInt(strInput);
                inputColunaValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
        tentativas++;
    }

    // if (strInput.matches("[0-9]+")) {
    // tiro[0] = Integer.parseInt(strInput);
    // inputValido = true;
    // }

    // TODO: O if das funcoes imprimirMsgAcertos e imprimirMsgErros nao esta
    // funcionando direito. Contador de erros e acertos nao estao functionando

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

    public static boolean verificarTiro(int[] tiro, int[][] submarinos, int acertos, int erros) {
        for (int submarino = 0; submarino < submarinos.length; submarino++) {
            if (tiro[0] == submarinos[submarino][0] && tiro[1] == submarinos[submarino][1]) {
                acertos++;
                imprimirMsgAcertos(tiro, acertos);
                return true;
            }
        }
        erros++;
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

    public static boolean verificarFimDoJogo(int acertos, int NUMERO_SUBMARINOS, boolean acabouJogo) {
        if (acertos == NUMERO_SUBMARINOS) {
            System.out.println("Parabens! Voce acertou todos os submarinos!");
            acabouJogo = true;
        }
        return acabouJogo;
    }
}
