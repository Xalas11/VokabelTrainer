import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Diese Klasse verwaltet die Vokabel abfrage
 */

public class Lesson {

    private LinkedList<WordPair> lessonSet;
    private LinkedList<WordPair> lektion0 = new LinkedList<>();
    private int lektion;
    static public int startLang = 0;
    static public int destiLang = 1;
    static public int boarderof0 = -2;

    /**
     *
     * @param lessonSet
     * @param lektion
     */
    public Lesson(LinkedList<WordPair> lessonSet, int lektion) {
        this.lessonSet = lessonSet;
        this.lektion = lektion;
    }

    /**
     * Waehle zufaellige Sprache
     * @return
     */
    public int rndLanguage() {
        Random rnd = new Random();
        int randomNum = rnd.nextInt(2 - 0) + 1;
        if (randomNum == 1) {
            return destiLang;
        } else {
            return startLang;
        }
    }

    /**
     * Schaut ob String a mit String b uebereinstimmt
     * @param a
     * @param b
     * @return
     */
    private boolean matcher(String a, String b) {
        if (a.equals(b)) return true;
        return false;
    }
    /**
     * fuegt string lektion 1 hinzu
     * @param addto0
     */
    private void addToLek0(WordPair addto0) {
        if ((addto0.getTrainingLvl() <= boarderof0 )) {
            lektion0.addLast(addto0);
        }
    }
    /**
     *  Erneuert die Lektionen loescht tranierte Vokabeln und leitet Lektion 0 Vokabeln weiter
     * @throws IOException
     */
    private void refreshLektions() throws IOException {
        Document.textToDocument(0, lektion0);
        // wenn es sich um Vokabeln handelt die 3mal richtig geschrieben worden werden diese nach der Abfrage aus der Lektion gelöscht
        Document.textToDocument(lektion, lessonSet);
    }

    /**
     *  Vokabeln werden in Eingabereihenfolge abgefragt
     * @throws IOException
     */
    public void firstToLast() throws IOException {
        // geht die liste von vorn nach hinten durch
        if (!lessonSet.isEmpty()) {
            for (int i = 0; i < lessonSet.size(); i++) {
                if (lektion == lessonSet.get(i).getLektionNumber()) {
                    System.out.println("Vokabel: " + lessonSet.get(i).getVocabulary()[startLang] + " Sie sind Dran: ");
                    String temp = CheckInput.cString();
                    if (matcher(lessonSet.get(i).getVocabulary()[destiLang], temp)) { //note muss das übersetzte wort matchen nicht das gleiche
                        System.out.println(temp + " ist Richtig");
                        lessonSet.get(i).setTrainigLvl(true);
                    } else {
                        System.out.println("Das war leider Falsch: " + lessonSet.get(i).getVocabulary()[destiLang]);
                        lessonSet.get(i).setTrainigLvl(false);
                        addToLek0(lessonSet.get(i));
                    }
                }
            }
        }
        refreshLektions();
    }

    /**
     * Vokabeln werden durcheinander Abgefraget, sprachen und reihenfolge
     * @throws IOException
     */
    public void random() throws IOException {
        // geht die liste random durch, die abgefrage sprache ist ebenfalls random
        LinkedList<Integer> preMutate = new LinkedList<>();
        for (int i = 0; i < lessonSet.size(); i++) {
            preMutate.add(i);
        }
        Collections.shuffle(preMutate);
        for (int i = 0; i < lessonSet.size(); i++) {
            int lang = rndLanguage();
            System.out.println(lessonSet.get(preMutate.getFirst()).getVocabulary()[lang] + " Sie sind Dran: ");
            String temp = CheckInput.cString();
            if (lang == startLang) {
                lang = destiLang;
            } else {
                lang = startLang;
            }
            if (matcher(lessonSet.get(preMutate.getFirst()).getVocabulary()[lang], temp)) { //note muss das übersetzte wort matchen nicht das gleiche
                System.out.println(temp + " ist Richtig");
                lessonSet.get(preMutate.getFirst()).setTrainigLvl(true);
            } else {
                System.out.println("Das war leider Falsch: " + lessonSet.get(preMutate.getFirst()).getVocabulary()[lang]);
                lessonSet.get(preMutate.getFirst()).setTrainigLvl(false);
                addToLek0(lessonSet.get(i));
            }
            preMutate.removeFirst();
        }
        refreshLektions();
    }
}

