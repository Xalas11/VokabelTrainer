/**
 * Die Klasse stellt ein Vokabelpaar da
 */
public class WordPair {
    private int lessonNumber = 0;
    private int trainingLvl = 0;
    private String[] vocabulary;

    /**
     *
     * @param lektionNumber
     * @param vocabulary
     */
    public WordPair(int lektionNumber, String[] vocabulary) {
        this.vocabulary = vocabulary;
        this.lessonNumber = lektionNumber;
    }

    /**
     *
     * @param lektionNumber
     * @param vocabulary
     * @param trainingLvl
     */
    public WordPair(int lektionNumber, String[] vocabulary, int trainingLvl) {
        this.vocabulary = vocabulary;
        this.lessonNumber = lektionNumber;
        this.trainingLvl = trainingLvl;
    }

    /**
     * gibt die Lektionsnummer der Vokabel zurueck
     * @return
     */
    public int getLektionNumber() {
        return lessonNumber;
    }

    /**
     * gibt das Vokabular des Objekts zurueck
     * @return
     */
    public String[] getVocabulary() {
        return vocabulary;
    }

    /**
     * gibt das Traningslvl des Objekts
     * @return
     */
    public int getTrainingLvl() {
        return trainingLvl;
    }

    /**
     * veraendert das Traningslvl
     * @param win
     */
    public void setTrainigLvl(boolean win) {
        if (win) {
            trainingLvl++;
        } else {
            if (trainingLvl > Lesson.boarderof0) trainingLvl--;
        }
    }

    /**
     * setzt die Lektionsnummer
     * @param lektionNumber
     */
    public void setLektionNumber(int lektionNumber) {
        this.lessonNumber = lektionNumber;
    }
}
