import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class ReadText {

    public static LinkedList<WordPair> readFile(String fileName) throws IOException {
        LinkedList<WordPair> wordList = new LinkedList<>();
        FileReader getText = new FileReader(fileName);
        BufferedReader bufferText = new BufferedReader(getText);


        String line = bufferText.readLine();
        if(!line.isEmpty()) {
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
}
