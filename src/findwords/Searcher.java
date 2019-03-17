package findwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Your implementation of the coursework.
 * This is the only source file you should modify, and the only one you
 * should submit.  The signatures of these methods should not be modified.
 */
public class Searcher {

    /**
     * Compare the front part of two character arrays for equality.
     * @param s the first character array
     * @param t the second character array
     * @param n number of characters to compare
     * @return true if s and t are equal up to the first n characters
     */
    public boolean equal(String s, String t, int n) {
        if(s.length() < n || t.length() < n){
            if(s.length() == t.length()){
                // invariant: i < length of s and s[i] == t[i]
                for(int i = 0; i < s.length(); i++){
                    if(s.charAt(i) != t.charAt(i)){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        // invariant: 0 <= i < n and s[i] == t[i]
        for(int i = 0; i < n; i++){
            if(s.charAt(i) != t.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Compare the front part of two character arrays.
     * @param s the first character array
     * @param t the second character array
     * @param n number of characters to compare
     * @return true if s is less than t in the first n characters
     */
    public boolean lessThan(String s, String t, int n) {
        // invariant: 0 < i < n and s[i] == t[i] and s and t have not been indexed out of range
        for (int i = 0; i < n; i++) {
            try{
                if(s.charAt(i) != t.charAt(i)){
                    return s.charAt(i) < t.charAt(i);
                }
            }catch(IndexOutOfBoundsException exc){
                return t.length() > s.length();
            }
        }
        return !equal(s, t, n);
    }

    /**
     * Find the first position of a prefix in a dictionary.
     * @param d an ordered dictionary of words
     * @param w a prefix to search for
     * @param n number of characters to compare
     * @return the least index such that all earlier entries in the dictionary
     * are smaller than e when comparing the first n characters.
     */
    public int findPrefix(Dictionary d, String w, int n) {
        // replace the following line with your implementation
        int lowerBound = 0;
        int higherBound = d.size() - 1;

        // invariant: lowerbound less than or equal to higherbound
        while(lowerBound <= higherBound){
            int mid = (lowerBound + higherBound) / 2;
            if(lessThan(d.getWord(mid), w, n)){
                lowerBound = mid + 1;
            }else{
                higherBound = mid - 1;
            }
        }
        return lowerBound;
    }

    /**
     * Search a dictionary for words matching a clue.
     * @param d an ordered dictionary of words
     * @param clue a word to search for, with . standing for any character
     * @return a list of all the words in the dictionary that match the clue
     */
    public ArrayList<String> findMatches(Dictionary d, String clue) {
        ArrayList<String> matches = new ArrayList<>();

        int prefix = clue.length();
        // invariant: character
        for(int character = 0; character < prefix; character++){
            if(clue.charAt(character) == '.'){
                prefix = character;
                break;
            }
        }

        System.out.println("prefix " + prefix);

        int calls = 0;
        // invariant: findPrefix(d,clue,prefix) < i < dict.size()
        for(int i = findPrefix(d, clue, prefix); i < d.size(); i++){
            if(prefix > 0){
                String wordToCompare = d.getWord(i);
                calls++;
                boolean assumeMatch = true;
                if(equal(wordToCompare, clue, prefix)){
                    if(wordToCompare.length() == clue.length()){
                        // invariant: j < clue.length()
                        for(int j = 0; j < clue.length(); j++){
                            if(wordToCompare.charAt(j) != clue.charAt(j) && clue.charAt(j) != '.'){
                                assumeMatch = false;
                            }
                        }
                        if(assumeMatch) matches.add(wordToCompare);
                    }
                }else{
                    break;
                }
            }else {
                // invariant: i < d.size()
                for (i = findPrefix(d, clue, prefix); i < d.size(); i++) {
                    String wordToCompare = d.getWord(i);
                    calls++;
                    boolean assumeMatch = true;
                    if (wordToCompare.length() == clue.length()) {
                        // invariant: j < clue.length()
                        for (int j = 0; j < clue.length(); j++) {
                            if (wordToCompare.charAt(j) != clue.charAt(j) && clue.charAt(j) != '.') {
                                assumeMatch = false;
                            }
                        }
                        if (assumeMatch) matches.add(wordToCompare);
                    }
                }
            }
        }

        System.out.println(clue + " called getword " + calls + " times");
        return matches;
    }
    
}
