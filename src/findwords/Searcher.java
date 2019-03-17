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

    // my own method, returns the length of the shortest of the two input strings
    public int lengthOfShortestInput(String s, String t){
        if(s.length() < t.length()){
            return s.length();
        }
        return t.length();
    }

    /**
     * Compare the front part of two character arrays.
     * @param s the first character array
     * @param t the second character array
     * @param n number of characters to compare
     * @return true if s is less than t in the first n characters
     */
    public boolean lessThan(String s, String t, int n) {
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
        int low = 0;
        int high = d.size() - 1;

        while(low < high){
            int mid = (low + high) / 2;
            if(lessThan(d.getWord(mid), w, n)){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * Search a dictionary for words matching a clue.
     * @param d an ordered dictionary of words
     * @param clue a word to search for, with . standing for any character
     * @return a list of all the words in the dictionary that match the clue
     */
    public ArrayList<String> findMatches(Dictionary d, String clue) {
        ArrayList<String> matches = new ArrayList<>();

        Pattern p = Pattern.compile(clue);

        int indexForPrefixCheck = 0;
        for(int i = 0; i < clue.length(); i++){
            if(clue.charAt(i) != '.'){
                indexForPrefixCheck = i + 1;
            }else{
                break;
            }
        }
        int indexOfFirstMatch = findPrefix(d, clue, indexForPrefixCheck) + 1;

        for(int i = indexOfFirstMatch; i < indexOfFirstMatch + 1000; i++){
            String word = d.getWord(i);
            if(p.matcher(word).matches()){
                matches.add(word);
            }
        }

        //System.out.println(d.getWord(findPrefix(d, clue, )));
        return matches;
    }
    
}
