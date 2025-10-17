package util;

import ds.trie.ArrayBasedTrieNode;
import ds.trie.MapBasedTrieNode;
import ds.trie.TrieNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrieUtil {
    public static TrieNode<ArrayBasedTrieNode[]> createArrayBasedWordTrie(List<String> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Invalid Input - Unable to create Trie");
        }
        TrieNode<ArrayBasedTrieNode[]> trie = new ArrayBasedTrieNode();
        for (String word : words) {
            TrieNode<ArrayBasedTrieNode[]> temp = trie;
            word = word.toLowerCase();
            int wordLen = word.length();
            for (int i = 0; i < wordLen; i++) {
                int index = word.charAt(i) - 'a';
                if (temp.getChildren()[index] != null) {
                    temp = temp.getChildren()[index];
                    continue;
                }
                temp.getChildren()[index] = new ArrayBasedTrieNode();
                temp = temp.getChildren()[index];
            }
            if (temp.getWordCount() == 0) {
                temp.incrementWordCount();
            }
        }
        return trie;
    }

    public static TrieNode<Map<Character, MapBasedTrieNode>> createMapBasedWordTrie(List<String> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Invalid Input - Unable to create Trie");
        }
        TrieNode<Map<Character, MapBasedTrieNode>> trie = new MapBasedTrieNode();
        for (String word : words) {
            TrieNode<Map<Character, MapBasedTrieNode>> temp = trie;
            word = word.toLowerCase();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (temp.getChildren().containsKey(ch)) {
                    temp = temp.getChildren().get(ch);
                    continue;
                }
                temp.getChildren().put(ch, new MapBasedTrieNode());
                temp = temp.getChildren().get(ch);
            }
            if (temp.getWordCount() == 0) {
                temp.incrementWordCount();
            }
        }
        return trie;
    }

    public static TrieNode<?> createTrieFromDictionaryFile(TrieType trieType) {
        List<String> words;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/dictionary"))) {
            words = new ArrayList<>();
            String word;
            while ((word = bufferedReader.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (trieType) {
            case ARRAY -> {
                return createArrayBasedWordTrie(words);
            }
            case MAP -> {
                return createMapBasedWordTrie(words);
            }
            default -> throw new IllegalArgumentException("Trie Creation Error - Cannot create " + trieType.name() + " based Trie");
        }
    }

    public enum TrieType {
        ARRAY("array"),
        MAP("map");

        private final String type;
        TrieType(String type) {
            this.type = type;
        }
        //This function would also work without defining additional property 'type' in the enum and
        //without providing any constructor by using name() method for comparison
        public static TrieType getTrieType(String type) {
            if (CommonUtil.isValidAlphabeticString(type)) {
                for (TrieType trieType : TrieType.values()) {
                    //Would also work using - type.equalsIgnoreCase(trieType.name())
                    //with no need for (private final String type;) and the constructor
                    if (type.equalsIgnoreCase(trieType.type)) {
                        return trieType;
                    }
                }
            }
            throw new IllegalArgumentException("Invalid Trie Type - Provided Trie type doesn't exist");
        }
    }
}
