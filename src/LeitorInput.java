import java.util.Scanner;

public class LeitorInput {

  private Scanner scanner;

  LeitorInput() {
    scanner = new Scanner(System.in);
  }

  public int lerInteiro(String inputMsg, String errorMsg, int limiteMin, int limiteMax) {
    int numero = 0;
    String strInput;
    boolean inputValido = false;

    Scanner scanner = new Scanner(System.in);

    // Loop para verificar se o input do usuario e valido
    while (inputValido == false) {
      System.out.print(inputMsg);
      strInput = scanner.nextLine();
      try {
        numero = Integer.parseInt(strInput);
        if (numero >= limiteMin && numero < limiteMax) {
          inputValido = true;
        } else {
          System.out.print(errorMsg);
        }
      } catch (NumberFormatException e) {
        System.out.print(errorMsg);
      }
    }
    return numero;
  }
}
