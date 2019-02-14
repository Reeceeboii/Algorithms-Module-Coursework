package findwords;

import java.util.ArrayList;


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
            return true;
        }

        // invariant: 0 <= i < n and s[i] == t[i]
        for(int i = 0; i < n; i++){
            if(s.charAt(i) != t.charAt(i)){
                return false;
            }
        }
        return true;
    }

    private String findShortest(String s, String t){
        if(s.length() < t.length()) {
            return s;
        }
        return t;
    }

    /**
     * Compare the front part of two character arrays.
     * @param s the first character array
     * @param t the second character array
     * @param n number of characters to compare
     * @return true if s is less than t in the first n characters
     */
    public boolean lessThan(String s, String t, int n) {
        if(!(s.length() < n) || (t.length() < n)){
            // invariant i <= 0 < n and s[i] == t[i]
            for(int i = 0; i < n; i++){
                if(s.charAt(i) != t.charAt(i)){
                    return s.charAt(i) < t.charAt(i);
                }
            }
            // in this case, i = n and no case for s[i] != t[i] has been found; equality in all chars <= n
            return false;
        }
        // any inputs where n is larger than either input
        // TODO got here
        return false;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Search a dictionary for words matching a clue.
     * @param d an ordered dictionary of words
     * @param clue a word to search for, with . standing for any character
     * @return a list of all the words in the dictionary that match the clue
     */
    public ArrayList<String> findMatches(Dictionary d, String clue) {
        ArrayList<String> matches = new ArrayList<>();
        // fill in your implementation here to add to matches here
        return matches;
    }
    
}
