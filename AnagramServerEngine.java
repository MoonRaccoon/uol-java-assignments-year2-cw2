import java.util.Arrays;
import java.util.List;

public class AnagramServerEngine {
    private AnagramFactory anagramFactory;
    private PermutationFactory permutationFactory;

    public AnagramServerEngine() {
        anagramFactory = AnagramFactory.getInstance();
        permutationFactory = PermutationFactory.getInstance();
    }

    public String getAvailableCommands() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%n", "ANAGRAM <word>  show all anagrams of the word <word>"));
        sb.append(String.format("%s%n", "PERMUTE <word>  show all permutations of the word <word>"));
        sb.append(String.format("%s%n", "SHOW HELP            show this help"));
        return sb.toString();
    }

    public String parseCommand(String command) {
        //each command has an instruction and an argument. split the incoming string on the first whitespace character (or whitespace characterS if they are contiguous)
        String[] words = command.split("\\s+", 2);
        if (words.length < 2) {
            return "Syntax: <command> <argument>.";
        }

        //make both strings lower case and trim any excess whitespace to make comparisons easier
        String instruction = words[0].toLowerCase().trim();
        String argument = words[1].toLowerCase().trim();

        switch (instruction) {
            case "show":
                return show(argument);
            case "anagram":
                return anagram(argument);
            case "permute":
                return permutation(argument);
            default: //everything that isn't a known command
                return "I don't understand '" + instruction + "'.";
        }
    }

    private String show(String command) {
        switch (command.toLowerCase()) {
            case "help":		return getAvailableCommands();

            default: 			return "I don't know how to show that!";
        }
    }

    private String anagram(String word) {
        Anagram a = anagramFactory.createAnagram(word);
        String anagrams = formatWords(a.getAnagrams());
        return String.format("Anagrams of %s: %s%n", word, anagrams);
    }

    private String permutation(String word) {
        Permutation p = permutationFactory.createPermutation(word);
        String permutations = formatWords(p.getPermutations());
        return String.format("Permutations of %s: %s%n", word, permutations);
    }

    private String formatWords(List<String> words) {
        StringBuffer output = new StringBuffer();
        for (String s : words) {
            output.append(System.lineSeparator());
            output.append(s);
        }

        if (output.length() == 0) {
            output.append("<none found>");
        }

        return output.toString();
    }

    public static void main(String[] args) {
        new AnagramServer();
    }


}

