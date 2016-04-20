import java.util.Scanner;

/**
 * Kleine Klasse um nicht jedesmal den input von scannern zu handeln
 */
public class CheckInput {
    /**
     *
     * @return
     */
    public static int cInt() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return sc.nextInt();
            }
            catch (java.util.InputMismatchException e) {
                sc.nextLine();
            }
            System.out.println("Falsche Eingabe: ");
        }
    }

    /**
     *
     * @return
     */
    public static String cString() {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        return a;
    }
}
