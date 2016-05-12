/**
 * Die Klasse stellt ein Vokabelpaar da
 */
public class WordPair {
    private int lessonNumber = 0;
    private int trainingLvl = 0;
    private String[] vocabulary;

    /**
     *
     * @param lektionNumber lektions Nummer
     * @param vocabulary String Array mit Vokabular
     */
    public WordPair(int lektionNumber, String[] vocabulary) {
        this.vocabulary = vocabulary;
        this.lessonNumber = lektionNumber;
    }

    /**
     *
     * @param lektionNumber lektions Nummer
     * @param vocabulary String Array mit Vokabular
     * @param trainingLvl Integer mit TraningsLevel
     */
    public WordPair(int lektionNumber, String[] vocabulary, int trainingLvl) {
        this.vocabulary = vocabulary;
        this.lessonNumber = lektionNumber;
        this.trainingLvl = trainingLvl;
    }

    /**
     *
     * @return lessenNumber gibt die Lektionsnummer der Vokabel zurueck
     */
    public int getLektionNumber() {
        return lessonNumber;
    }

    /**
     *
     * @return gibt das Vokabular des Objekts zurueck
     */
    public String[] getVocabulary() {
        return vocabulary;
    }

    /**
     *
     * @return gibt das Traningslvl des Objekts
     */
    public int getTrainingLvl() {
        return trainingLvl;
    }

    /**
     *
     * @param win veraendert das Traningslvl
     */
    public void setTrainigLvl(boolean win) {
        if (win) {
            trainingLvl++;
        } else {
            if (trainingLvl > Lesson.boarderof0) trainingLvl--;
        }
    }

    /**
     *
     * @param lektionNumber setzt die Lektionsnummer
     */
    public void setLektionNumber(int lektionNumber) {
        this.lessonNumber = lektionNumber;
    }
}
