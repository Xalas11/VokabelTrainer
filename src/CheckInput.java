import java.util.Scanner;

/**
 * diese Klasse beherbergt statische Methoden um die Eingabe zu Pruefen
 */
public class CheckInput {
    /**
     * Gibt einen validen Integer zurzueck
     * @return einen gueltigen Integer
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
     * Gibt einen String zurueck
     * @return Gibt einen String zurueck
     */
    public static String cString() {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        return a;
    }
}
