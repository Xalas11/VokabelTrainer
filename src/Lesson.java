import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by robert on 4/15/16.
 */

public class Lesson {

    private LinkedList<WordPair> lessonSet;
    private LinkedList<WordPair> delete = new LinkedList<>();
    private int lektion;
    private int startLang = 0;
    private int destiLang = 1;

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
     *
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
     *
     * @param a
     * @param b
     * @return
     */
    public boolean matcher(String a, String b) {
        if (a.equals(b)) return true;
        return false;
    }

    /**
     *
     * @param deleteIndex
     */
    private void wantToDelete(WordPair deleteIndex) {
        System.out.print("Wenn sie die Vokabel nach dieser Session aus der Lektion löschen wollen geben sie bitte 'ja' ein");
        if (CheckInput.cString().equals("ja")) {
            delete.addFirst(deleteIndex);
        }
    }

    /**
     *
     * @throws IOException
     */
    private void delete() throws IOException {
        // wenn es sich um die 0te Lektion handelt werden Markierte Vokabeln gelöscht
        if (0 == lektion) {
            while (!delete.isEmpty()) {
                    lessonSet.remove();
                    delete.removeFirst();
            }
        }
        // wenn es sich um Vokabeln handelt die 3mal richtig geschrieben worden werden diese nach der Abfrage aus der Lektion gelöscht
            int arrayLenth = lessonSet.size();
            for (int i = 0; i < arrayLenth; i++) {
                if (lessonSet.get(i).getTrainingLvl() >= 3) {
                    lessonSet.remove(i);
                    arrayLenth--;
                    i--;
                }
            }
        PrintText pT = new PrintText();
        pT.textToDocument(lektion, lessonSet);
    }

    /**
     *
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
                        if (0 == lessonSet.get(i).getLektionNumber()) {
                            wantToDelete(lessonSet.get(i));
                        }
                        lessonSet.get(i).setTrainigLvl(true);
                    } else {
                        System.out.println("Das war leider Falsch: " + lessonSet.get(i).getVocabulary()[destiLang]);
                        lessonSet.get(i).setTrainigLvl(false);
                    }
                }
            }
        }
        delete();
    }

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
                if (0 == lessonSet.get(i).getLektionNumber()) {
                    wantToDelete(lessonSet.get(i));
                }
            } else {
                System.out.println("Das war leider Falsch: " + lessonSet.get(preMutate.getFirst()).getVocabulary()[lang]);
                lessonSet.get(preMutate.getFirst()).setTrainigLvl(false);
            }
            preMutate.removeFirst();
        }
        delete();
    }
    public LinkedList<WordPair> getLessonSet() {
        return lessonSet;
    }
}

