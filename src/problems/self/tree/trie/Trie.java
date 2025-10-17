package problems.self.tree.trie;

import ds.trie.ArrayBasedTrieNode;
import ds.trie.MapBasedTrieNode;
import ds.trie.TrieNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.CommonUtil;
import util.TrieUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode<?> trie;
    private TrieUtil.TrieType trieType;

    public Trie() {
        createTrie(null);
    }

    public Trie(List<String> words) {
        createTrie(words);
    }

    private void createTrie(List<String> words) {
        this.trieType = getTrieType();
        if (words != null && !words.isEmpty()) {
            System.out.println("----CREATING TRIE FROM INPUT LIST OF WORDS----");
            if (this.trieType == TrieUtil.TrieType.ARRAY) {
                this.trie = TrieUtil.createArrayBasedWordTrie(words);
            } else if (this.trieType == TrieUtil.TrieType.MAP) {
                this.trie = TrieUtil.createMapBasedWordTrie(words);
            }
        } else {
            System.out.println("----CREATING TRIE FROM DICTIONARY FILE----");
            this.trie = TrieUtil.createTrieFromDictionaryFile(this.trieType);
        }
        if (this.trie != null) {
            System.out.println("----" + this.trieType.name() + " based Trie created successfully----");
        } else {
            throw new IllegalStateException("Trie Creation Error. Exiting...");
        }
    }

    private TrieUtil.TrieType getTrieType() {
        //Using BufferedReader for input
        //BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))
        //without BUFFER_SIZE can also be used since a default BUFFER_SIZE of 8192 is already initialized within the library class.
        try(FileReader trieTypeReader = new FileReader("resources/config.json")) {
            JSONObject trieConfig = (JSONObject) ((JSONObject) new JSONParser().parse(trieTypeReader)).get("trie");
            String trieTypeString = (String) trieConfig.get("trieType");
            TrieUtil.TrieType trieType = TrieUtil.TrieType.getTrieType(trieTypeString);
            System.out.println("[INFO] " + trieType.name() + " based Trie selected for creation");
            return trieType;
            //Not using this stream method for Trie type check.
            //Instead, defined the same functionality in enum itself which is called above.
            //The additional assignment to finalTrieTypeString is needed to make trieTypeString effectively final
            //which is mandatory for it to be accepted as Predicate in noneMatch() function below.
            /*String finalTrieTypeString = trieTypeString;
            if (Arrays.stream(TrieUtil.TrieType.values())
                    .noneMatch(currTrieType -> currTrieType.name().equalsIgnoreCase(finalTrieTypeString))) {
                System.err.println("Invalid Input - Only array and map type Trie supported");
            }*/
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsPrefix(String prefix) {
        return isPresent(prefix, false);
    }

    public boolean containsWord(String word) {
        return isPresent(word, true);
    }

    private boolean isPresent(String prefix, boolean completeWordCheck) {
        if (!CommonUtil.isValidAlphabeticString(prefix)) {
            return false;
        }
        prefix = prefix.toLowerCase();
        if (this.trieType == TrieUtil.TrieType.ARRAY) {
            ArrayBasedTrieNode temp = (ArrayBasedTrieNode) this.trie;
            for (int i = 0; i < prefix.length(); i++) {
                char currChar = prefix.charAt(i);
                int currIndex = currChar - 'a';
                if (temp.getChildren()[currIndex] == null) {
                    return false;
                }
                temp = temp.getChildren()[currIndex];
            }
            if (completeWordCheck) {
                return temp.getWordCount() > 0;
            }
        } else if (this.trieType == TrieUtil.TrieType.MAP) {
            MapBasedTrieNode temp = (MapBasedTrieNode) this.trie;
            for (int i = 0; i < prefix.length(); i++) {
                char currChar = prefix.charAt(i);
                if (!temp.getChildren().containsKey(currChar)) {
                    return false;
                }
                temp = temp.getChildren().get(currChar);
            }
            if (completeWordCheck) {
                return temp.getWordCount() > 0;
            }
        }
        return true;
    }

    public List<String> search(String prefix) {
        List<String> words = new ArrayList<>();
        if (!this.containsPrefix(prefix)) {
            return words;
        }
        if (this.trieType == TrieUtil.TrieType.ARRAY) {
            ArrayBasedTrieNode temp = (ArrayBasedTrieNode) this.trie;
            for (int i = 0; i < prefix.length(); i++) {
                char currChar = prefix.charAt(i);
                int currIndex = currChar - 'a';
                //null check is already performed in containsPrefix(String prefix) function and need not be done again
                //hence directly assigning temp to temp.children
                temp = temp.getChildren()[currIndex];
            }
            if (temp.getWordCount() > 0) {
                words.add(prefix);
            }
            getWordsFromArrayBasedTrie(temp, words, prefix);
        } else if (this.trieType == TrieUtil.TrieType.MAP) {
            MapBasedTrieNode temp = (MapBasedTrieNode) trie;
            for (int i = 0; i < prefix.length(); i++) {
                char currChar = prefix.charAt(i);
                //null check is already performed in containsPrefix(String prefix) function and need not be done again
                //hence directly assigning temp to temp.children
                temp = temp.getChildren().get(currChar);
            }
            if (temp.getWordCount() > 0) {
                words.add(prefix);
            }
            getWordsFromMapBasedTrie(temp, words, prefix);
        }
        return words;
    }

    private void getWordsFromArrayBasedTrie(ArrayBasedTrieNode temp, List<String> words, String prefix) {
        for (int i = 0; i < temp.getChildren().length; i++) {
            if (temp.getChildren()[i] != null) {
                char ch = (char)((int)'a' + i);
                String newPrefix = prefix + ch;
                if (temp.getChildren()[i].getWordCount() > 0) {
                    words.add(newPrefix);
                }
                getWordsFromArrayBasedTrie(temp.getChildren()[i], words, newPrefix);
            }
        }
    }

    private void getWordsFromMapBasedTrie(MapBasedTrieNode temp, List<String> words, String prefix) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (temp.getChildren().containsKey(ch)) {
                String newPrefix = prefix + ch;
                if (temp.getChildren().get(ch).getWordCount() > 0) {
                    words.add(newPrefix);
                }
                getWordsFromMapBasedTrie(temp.getChildren().get(ch), words, newPrefix);
            }
        }
    }

    public boolean add(String word) {
        if (!CommonUtil.isValidAlphabeticString(word)) {
            return false;
        }
        word = word.toLowerCase();
        if (this.trieType == TrieUtil.TrieType.ARRAY) {
            ArrayBasedTrieNode temp = (ArrayBasedTrieNode) this.trie;
            if (temp == null) {
                temp = new ArrayBasedTrieNode();
            }
            for (char ch : word.toCharArray()) {
                if (temp.getChildren()[ch - 'a'] == null) {
                    temp.getChildren()[ch - 'a'] = new ArrayBasedTrieNode();
                }
                temp = temp.getChildren()[ch - 'a'];
            }
            if (temp.getWordCount() == 0) {
                temp.incrementWordCount();
            }
        } else if (this.trieType == TrieUtil.TrieType.MAP) {
            MapBasedTrieNode temp = (MapBasedTrieNode) trie;
            if (temp == null) {
                temp = new MapBasedTrieNode();
            }
            for (char ch : word.toCharArray()) {
                if (!temp.getChildren().containsKey(ch)) {
                    temp.getChildren().put(ch, new MapBasedTrieNode());
                }
                temp = temp.getChildren().get(ch);
            }
            if (temp.getWordCount() == 0) {
                temp.incrementWordCount();
            }
        }
        return true;
    }

    public boolean delete(String word) {
        if (!CommonUtil.isValidAlphabeticString(word)) {
            System.out.println("[RESULT] '"+ word + "' not found in Trie");
            return false;
        }
        word = word.toLowerCase();
        if (this.trieType == TrieUtil.TrieType.ARRAY) {
            return deleteFromArrayTrie((ArrayBasedTrieNode) this.trie, word);
        } else if (this.trieType == TrieUtil.TrieType.MAP) {
            return deleteFromMapTrie((MapBasedTrieNode) this.trie, word);
        }
        return false;
    }

    private boolean deleteFromMapTrie(MapBasedTrieNode trie, String word) {
        MapBasedTrieNode temp = trie;
        MapBasedTrieNode lastNonDeletableNode = null;
        char lastDeletableChar = word.charAt(0);
        for (char character : word.toCharArray()) {
            if (temp.getChildren().isEmpty()) {
                System.out.println("[RESULT] '"+ word + "' not found in Trie");
                return false;
            }
            if (temp.getChildren().size() > 1) {
                lastNonDeletableNode = temp;
                lastDeletableChar = character;
            }
            temp = temp.getChildren().get(character);
        }
        if (temp.getWordCount() == 0) {
            System.out.println("[RESULT] '"+ word + "' not found in Trie");
            return false;
        }
        System.out.println("---Deleting---");
        //Scenario when the word to be deleted is a prefix for other word(s)
        if (!temp.getChildren().isEmpty()) {
            temp.decrementWordCount();
            return true;
        }
        //Scenario when the characters in the word to be deleted branch off to form other word(s)
        if (lastNonDeletableNode != null) {
            lastNonDeletableNode.getChildren().remove(lastDeletableChar);
            return true;
        }
        //Scenario when the word is a standalone word without branching off or acting as a prefix to other word(s)
        trie.getChildren().remove(lastDeletableChar);
        return true;
    }

    private boolean deleteFromArrayTrie(ArrayBasedTrieNode trie, String word) {
        ArrayBasedTrieNode temp = trie;
        ArrayBasedTrieNode lastNonDeletableNode = null;
        char lastDeletableChar = word.charAt(0);
        int charCount;
        for (char character : word.toCharArray()) {
            if (temp.getChildren()[character - 'a'] == null) {
                System.out.println("[RESULT] '"+ word + "' not found in Trie");
                return false;
            }
            charCount = 0;
            for (int i = 0; i < temp.getChildren().length; i++) {
                if (temp.getChildren()[i] != null) {
                    charCount++;
                }
            }
            if (charCount > 1) {
                lastNonDeletableNode = temp;
                lastDeletableChar = character;
            }
            temp = temp.getChildren()[character - 'a'];
        }
        if (temp.getWordCount() == 0) {
            System.out.println("[RESULT] '"+ word + "' not found in Trie");
            return false;
        }
        System.out.println("---Deleting---");
        charCount = 0;
        for (int i = 0; i < temp.getChildren().length; i++) {
            if (temp.getChildren()[i] != null) {
                charCount++;
            }
        }
        //Scenario when the word to be deleted is a prefix for other word(s)
        if (charCount > 0) {
            temp.decrementWordCount();
            return true;
        }
        //Scenario when a part of the word to be deleted is a prefix for other word(s)
        if (lastNonDeletableNode != null) {
            lastNonDeletableNode.getChildren()[lastDeletableChar - 'a'] = null;
            return true;
        }
        //Scenario when the word is a standalone word without branching off or acting as a prefix to other word(s)
        trie.getChildren()[lastDeletableChar] = null;
        return true;
    }
}
