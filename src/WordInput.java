import java.util.LinkedList;

/**
 * Created by robert on 4/15/16.
 */
public class WordInput {
    private LinkedList<WordPair> newWordList = new LinkedList<>();
    private int lektionNumber;
    public void makeWordList() {
        System.out.println("Bitte geben sie die Lektionsnummer der Vokabeln ein: ");
        lektionNumber = CheckInput.cInt();
        do {
            int i = 0;
            String[] wordPair = new String[Languages.values().length];
            for (Languages n : Languages.values()) {
                System.out.println("Vokabel in " + n + " eintippen: ");
                wordPair[i] = CheckInput.cString();
                i++;
            }
            WordPair temp = new WordPair(lektionNumber, wordPair);
            newWordList.addLast(temp);
            System.out.println("Um die Eingabe zu beenden geben sie 'end' ein ansonsten Enter fuer eine weitere Eingabe!");
        } while (!CheckInput.cString().equals("end"));
        Document.printWordPairList(newWordList);
    }
    public void resetList() {
        newWordList = null;
        newWordList = new LinkedList<>();
    }
    public LinkedList<WordPair> getNewWordList() {
        return newWordList;
    }

    public int getLektionNumber() {
        return lektionNumber;
    }
}
