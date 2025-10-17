package problems.self.tree.trie;

import java.io.Closeable;
import java.util.List;
import java.util.Scanner;

//Made the class Closeable so it can be used in try with resources
public class TrieFunction implements Closeable {
    private final Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        Trie trie = new Trie();
        //If a BufferedReader/another Scanner reading input from console is closed before current Scanner is used,
        //it closes the System.in stream as well.
        //After that any scanner read operation will give NoSuchElementException.
        //In such a scenario use the current Scanner initialization
        //within the BufferedReader/another Scanner's try's scope or before closing that stream.
        //catch/finally not mandatory when using try with resources
        try(TrieFunction trieFunc = new TrieFunction()) {
            //Java text block for multiple line statements
            System.out.print("""
                    
                    Functions available for Trie
                     - src (for searching words with matching prefix in Trie)
                     - add (for adding a word to Trie)
                     - del (for deleting a word from Trie)
                     - pre (for checking if prefix is present in Trie)
                     - wrd (for checking if entire word is present in Trie)
                    Enter your choice:\s""");
            switch (trieFunc.getWordInput().toLowerCase()) {
                case "src": {
                    System.out.print("Enter prefix for word search in Trie (a-z/A-Z) : ");
                    trieFunc.searchWordsInTrie(trie, trieFunc.getWordInput());
                    break;
                }
                case "add": {
                    System.out.print("Enter word to add to Trie (a-z/A-Z) : ");
                    trieFunc.addWordInTrie(trie, trieFunc.getWordInput());
                    break;
                }
                case "del": {
                    System.out.print("Enter word to delete from Trie (a-z/A-Z) : ");
                    trieFunc.deleteWordFromTrie(trie, trieFunc.getWordInput());
                    break;
                }
                case "pre": {
                    System.out.print("Enter prefix to check in Trie (a-z/A-Z) : ");
                    trieFunc.prefixCheckInTrie(trie, trieFunc.getWordInput());
                    break;
                }
                case "wrd": {
                    System.out.print("Enter word to check in Trie (a-z/A-Z) : ");
                    trieFunc.wordCheckInTrie(trie, trieFunc.getWordInput());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("!! Incorrect function selected for Trie !!");
                }
            }
        }
    }

    private void prefixCheckInTrie(Trie trie, String prefix) {
        System.out.println();
        if (trie.containsPrefix(prefix)) {
            System.out.println("[SUCCESS] Prefix '" + prefix + "' present in Trie");
        } else {
            System.out.println("[RESULT] No prefix '" + prefix + "' found in Trie");
        }
    }

    private void wordCheckInTrie(Trie trie, String word) {
        System.out.println();
        if (trie.containsWord(word)) {
            System.out.println("[SUCCESS] Word '" + word + "' present in Trie");
        } else {
            System.out.println("[RESULT] No word '" + word + "' found in Trie");
        }
    }

    private void deleteWordFromTrie(Trie trie, String word) {
        System.out.println();
        if (trie.delete(word)) {
            System.out.println("[SUCCESS] '" + word + "' deleted from Trie");
            validateBySearch(trie, word);
        } else {
            System.out.println("!!Unable to delete " + word + " from Trie!!");
        }
    }

    private void addWordInTrie(Trie trie, String word) {
        System.out.println();
        if (trie.add(word)) {
            System.out.println("[SUCCESS] '" + word + "' added to Trie");
            validateBySearch(trie, word);
        } else {
            System.out.println("!!Unable to add " + word + " to Trie!!");
        }
    }

    private void searchWordsInTrie(Trie trie, String prefix) {
        List<String> wordsInTrie = trie.search(prefix);
        System.out.println();
        if (wordsInTrie == null || wordsInTrie.isEmpty()) {
            System.out.println("[RESULT] No words with prefix '" + prefix + "' found in Trie");
        } else {
            System.out.println("[SUCCESS] " + wordsInTrie.size() + " word(s) with prefix '" + prefix + "' found in Trie");
            for (String word : wordsInTrie) {
                System.out.println(word);
            }
        }
    }

    private void validateBySearch(Trie trie, String word) {
        //For preserving whitespaces at the end of a text block line use \s
        System.out.println("""
                    ---Validating---
                    Searching for '""" + word + """ 
                    ' in Trie""");
        searchWordsInTrie(trie, word);
    }

    private String getWordInput() {
        return this.inputScanner.nextLine().trim();
    }

    @Override
    public void close() {
        this.inputScanner.close();
    }
}
