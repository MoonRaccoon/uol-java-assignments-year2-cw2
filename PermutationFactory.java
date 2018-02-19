import java.util.*;

public class PermutationFactory {

    private static PermutationFactory instance;
    public static PermutationFactory getInstance() {
        if (instance == null) {
            instance = new PermutationFactory();
        }
        return instance;
    }

    private Map<String, Permutation> cache;

    private PermutationFactory() {
        cache = new HashMap<>();
    }

    public Permutation createPermutation(String word) {
        word = word.toLowerCase();
        if (cache.containsKey(word)) {
            return cache.get(word);
        }
        List<String> permutations = new ArrayList<>(permute(word));

        //we don't consider a word to be a permutation of itself, so remove it if it's in the List
        if (permutations.contains(word)) {
            permutations.remove(word);
        }

        Permutation permutation = new Permutation(word, permutations);
        cache.put(word, permutation);

        return permutation;
    }

    //modified from https://stackoverflow.com/q/4240080
    private TreeSet<String> permute(String s) {
        TreeSet<String> permutations = new TreeSet<>();
        if(s.length() == 1) {
            permutations.add(s);
        } else {
            for (int i=0; i < s.length(); i++) {
                TreeSet<String> temp = permute(s.substring(0, i)+s.substring(i+1));
                for (String string : temp) {
                    permutations.add(s.charAt(i)+string);
                }
            }
        }
        return permutations;
    }
}
