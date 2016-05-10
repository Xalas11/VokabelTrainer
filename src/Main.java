import java.io.IOException;

/**
 * Main Klasse
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Document.getAllFiles();
        Launcher launch = new Launcher();
        launch.startProgram("");
    }
}
