import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFactory {

    private static AnagramFactory instance;
    public static AnagramFactory getInstance() {
        if (instance == null) {
            instance = new AnagramFactory();
        }
        return instance;
    }

    private Map<String, Anagram> cache;
    private Dictionary dictionary;
    private PermutationFactory permutationFactory;

    private AnagramFactory() {
        cache = new HashMap<>();
        dictionary = Dictionary.getInstance();
        permutationFactory = PermutationFactory.getInstance();
    }

    public Anagram createAnagram(String word) {
        word = word.toLowerCase();
        if (cache.containsKey(word)) {
            return cache.get(word);
        }

        Permutation p = permutationFactory.createPermutation(word);
        List<String> anagrams = filter(p.getPermutations());

        Anagram anagram = new Anagram(word, anagrams);
        cache.put(word, anagram);
        return anagram;
    }

    private List<String> filter(List<String> permutations) {
        //take out any permutations that aren't in the dictionary
       List<String> anagrams = new ArrayList<>();
       for (String s : permutations) {
           if (dictionary.contains(s)) {
               anagrams.add(s);
           }
       }
       return anagrams;
    }
}
