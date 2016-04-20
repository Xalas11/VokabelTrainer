/**
 * Created by robert on 4/15/16.
 */
public class WordPair {
    private int lessonNumber = 0;
    private int trainingLvl = 0;
    private String[] vocabulary;

    public WordPair(int lektionNumber, String[] vocabulary) {
        this.vocabulary = vocabulary;
        this.lessonNumber = lektionNumber;
    }
    public WordPair(int lektionNumber, String[] vocabulary, int trainingLvl) {
        this.vocabulary = vocabulary;
        this.lessonNumber = lektionNumber;
        this.trainingLvl = trainingLvl;
    }

    public int getLektionNumber() {
        return lessonNumber;
    }

    public String[] getVocabulary() {
        return vocabulary;
    }

    public int getTrainingLvl() {
        return trainingLvl;
    }

    public void setTrainigLvl(boolean win) {
        if (win) {
            trainingLvl++;
        } else {
            if (trainingLvl > 0) trainingLvl--;
        }
    }

    public void setLektionNumber(int lektionNumber) {
        this.lessonNumber = lektionNumber;
    }
}
