import java.awt.image.ImagingOpException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by robert on 4/18/16.
 */
public class PrintText {

    public static void printWordPairList(LinkedList<WordPair> toPrint) {
        StringBuilder sb = new StringBuilder();
        String session;
        for (WordPair n : toPrint) {
            for (int i = 0; i < n.getVocabulary().length; i++) {
                System.out.print(n.getVocabulary()[i]);
                if (i < n.getVocabulary().length - 1) {
                    System.out.print(" || ");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Die Methode merged exsistierende Lektionen und frisch eingegebene und löscht die duplikate!
     * @param a
     * @param merge
     * @return
     */
    private LinkedList<WordPair> merge(LinkedList<WordPair> a, LinkedList<WordPair> merge) {
        a.addAll(merge);
        int b =a.size();
        for (int k=0; k<b; k++) {
            for (int i=k+1; i<b; i++){
                if (a.get(k).getVocabulary()[0].equals(a.get(i).getVocabulary()[0])) {
                    System.out.println(a.get(k).getVocabulary()[0]+"     "+a.get(i).getVocabulary()[0]);
                    a.remove(i);
                    b--;
                }
            }
        }
        return a;
    }

    public void textToDocument(int lektionNumber, LinkedList<WordPair> list) throws IOException {
        LinkedList<WordPair> tempExisting;

        try {
            // Schaut ob es einen txt fil gibt mit dieser Lektions nummer
            File file = new File("vokabeln" + lektionNumber + ".txt");
            if (!file.exists()) {
            } else {
                // wenn dieser Exsistiert werden die vorhanden Vokabeln der Funktion eingelesen und verglichen, Duplikate gelöscht
                tempExisting = ReadText.readFile("vokabeln" + lektionNumber + ".txt");
                list = merge(list, tempExisting);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list.isEmpty()) return;
        StringBuilder sb = new StringBuilder();
        sb.append(lektionNumber + "$\n"); // Lektions Kopf, nummer
        /*
            Loopt durch die Liste und schreibt sie in den Stringbuilder
         */
        while (!list.isEmpty()) {
            for (int k = 0; k < list.getFirst().getVocabulary().length; k++) {
                sb.append(list.getFirst().getVocabulary()[k] + "$");
            }
            sb.append(list.getFirst().getTrainingLvl() + "$");
            list.removeFirst();
            sb.append("\n");
        }

        String toDocument = sb.toString();

        // Wenn noch kein txt file für die jeweilige Lektion besteht wird dieser erstellt und mit der Linkedlist gefüllt
        if (!toDocument.equals(lektionNumber + "$\n")) {
            try {

                File file = new File("vokabeln" + lektionNumber + ".txt");

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(toDocument);
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
