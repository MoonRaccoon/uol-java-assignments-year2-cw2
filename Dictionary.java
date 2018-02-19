import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class Dictionary {
    private static Dictionary instance;
    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }
    private Set<String> words;

    private Dictionary() {
        try {
            words = new HashSet<String>(Files.readAllLines(Paths.get("words.small")));
        } catch (IOException e) {
            System.err.println("dictionary file not found!");
            words = new HashSet<>(); //empty dictionary
        }
    }

    public boolean contains(String word) {
        return words.contains(word);
    }
}
