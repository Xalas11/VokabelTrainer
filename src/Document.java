import java.io.*;
import java.util.LinkedList;

/**
 * Diese Klasse enthaelt statische Methoden fuer die Ausgabe und das Einlesen von Daten
 */
public class Document {

    /**
     * Gibt eine linkedliste mit WordPait Objecten aus
     * @param toPrint
     */

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
     * Liest einen txt ein und erstellt daraus WordPair Objecte und fuegt diese in eine Linkedliste ein
     * @param fileName
     * @return
     * @throws IOException
     */
    public static LinkedList<WordPair> readFile(String fileName) throws IOException {
        LinkedList<WordPair> wordList = new LinkedList<>();
        FileReader getText = new FileReader(fileName);
        BufferedReader bufferText = new BufferedReader(getText);


        String line = bufferText.readLine();
        if (!line.isEmpty()) {
            int lessonNumber = Integer.parseInt(line.substring(0, line.indexOf('$'))); // erste Zeile seperat einlesen!
            int nextWordIndex;

            line = bufferText.readLine();

            do {
                String[] words = new String[2];
                int prevWordIndex;
                int count = 0;
                nextWordIndex = 0;
                while (line != null && nextWordIndex < line.length()) {
                    String wordTemp;
                    prevWordIndex = line.indexOf('$', nextWordIndex);
                    wordTemp = line.substring(nextWordIndex, prevWordIndex);
                    nextWordIndex = prevWordIndex + 1;
                    words[count] = wordTemp;
                    if (count < 1) {
                        count++;
                    } else {
                        if (nextWordIndex < line.length()) {
                            prevWordIndex = line.indexOf('$', nextWordIndex);
                            count = Integer.parseInt(line.substring(nextWordIndex, prevWordIndex));
                            nextWordIndex = prevWordIndex + 1;
                        } else {
                            count = 0;
                        }
                    }
                }
                WordPair temp = new WordPair(lessonNumber, words, count);
                wordList.addLast(temp);
                line = bufferText.readLine();
            } while (line != null);
        }
        return wordList;
    }

    /**
     * Die Methode merged exsistierende Lektionen und frisch eingegebene und löscht die duplikate!
     *
     * @param a
     * @param merge
     * @return
     */
    private static LinkedList<WordPair> merge(LinkedList<WordPair> a, LinkedList<WordPair> merge) {
        a.addAll(merge);
        int b = a.size();
        for (int k = 0; k < b; k++) {
            for (int i = k + 1; i < b; i++) {
                if (a.get(k).getVocabulary()[0].equals(a.get(i).getVocabulary()[0])) {
                    a.remove(i);
                    b--;
                }
            }
        }
        int arrayLenth = a.size();
        for (int i = 0; i < arrayLenth; i++) {
            if (a.get(i).getTrainingLvl() >= 3) {
                a.remove(i);
                arrayLenth--;
                i--;
            }
        }
        return a;
    }

    /**
     * Für das Loeschen einer Lektion
     * @param lektion
     */
    public static void deleteDocument(String lektion) {
        // Schaut ob es einen txt fil gibt mit dieser Lektions nummer
        File file = new File(lektion);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Erstellt einen Lektionsdatei mit hilfe einer WordPair Liste
     * @param lektionNumber
     * @param list
     * @throws IOException
     */
    public static void textToDocument(int lektionNumber, LinkedList<WordPair> list) throws IOException {
        LinkedList<WordPair> tempExisting;

        try {
            // Schaut ob es einen txt fil gibt mit dieser Lektions nummer
            File file = new File("vokabeln" + lektionNumber + ".txt");
            if (!file.exists()) {
            } else {
                // wenn dieser Exsistiert werden die vorhanden Vokabeln der Funktion eingelesen und verglichen, Duplikate gelöscht
                tempExisting = Document.readFile("vokabeln" + lektionNumber + ".txt");
                list = merge(list, tempExisting);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //if (list.isEmpty()) return;
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
