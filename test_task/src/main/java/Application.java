import service.Parser;
import service.Reader;

public class Application {

    public static void main(String[] args) {

        System.out.println("Starting application!");

        Reader.savePage();
        Parser.countWords();

        System.out.println("End work!");

    }
}
