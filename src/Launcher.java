import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by robert on 4/18/16.
 */
public class Launcher {

    public void startProgram(String a) throws IOException {
        String kindOf = a;
        String input;
        while (!kindOf.equals("end")) {
            System.out.println("Fuer Abbruch 'end' Eingeben");
            if (kindOf.equals("")) {
                System.out.println("Fuer eine Lektion 'lektion' eingeben und fuer mit neuen Vokabeln fuettern 'fuettern' eingeben vielleicht wollen sie auch das Programm beenden und die Lektion 0 loeschen 'loeschen': ");
            }
            do {
                input = CheckInput.cString();
                if (input.equals("end")) {
                    break;
                }
                if (input.equals("fuettern")) {
                    break;
                }
                if (input.equals("lektion")) {
                    break;
                }
                if (input.equals("loeschen")) {
                    break;
                }
                System.out.println("Falsche Eingabe");
            } while (true);
            switch (input) {
                case "end":
                    return;
                case "fuettern":
                    fuettern();
                    return;
                case "lektion":
                    artAbfrage();
                    return;
                case "loeschen":
                    Document.deleteDocument("vokabeln0.txt");
                    return;
            }
        }
    }

    public void fuettern() throws IOException {
        WordInput wp = new WordInput();
        wp.makeWordList();
        Document pT = new Document();
        pT.textToDocument(wp.getLektionNumber(), wp.getNewWordList());
        startProgram("");
    }

    public void artAbfrage() throws IOException {
        Lesson l;
        System.out.println("Bitte geben sie die Lektions Nummer ein..");
        LinkedList<WordPair> check = new LinkedList<>();
        int checked;
        while (true) {
            checked = CheckInput.cInt();
            File file = new File("vokabeln" + checked + ".txt");
            if (file.exists()) {
                break;
            } else {
                System.out.println("Lektion existiert nicht geben sie eine andere Lektionsnummer ein: ");
            }
        }
        System.out.println("Fuer Abbruch 'end' Eingeben");
        System.out.println("Fuer von vorn nach hinten 'normal', Für zufaellige Reihenfolge und sprache 'random' Eingeben:");
        String input;
        int a;
        do {
            input = CheckInput.cString();
            if (input.equals("normal")) {
                a=1;
                break;
            }
            if (input.equals("random")) {
                a=2;
                break;
            }
            if (input.equals("end")){
                return;
            }
        } while (true);
        l = new Lesson(ReadText.readFile("vokabeln" + checked + ".txt"), checked);
        switch (a) {
            case 1:
                l.firstToLast();
                break;
            case 2:
                l.random();
                break;
        }
        System.out.println("Sie sind mit dieser Lektion Fertig wollen sie noch eine Lektion? 'ja'  für noch eine Lektion oder irgendwas für Hauptmenu");
        if(CheckInput.cString().equals("ja")){
            artAbfrage();
        } else {
            startProgram("");
        }
    }
}
