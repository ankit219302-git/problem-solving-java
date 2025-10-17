package ds.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Map based Trie for storing lowercase alphabets
 */
public class MapBasedTrieNode implements TrieNode<Map<Character, MapBasedTrieNode>> {
    private final Map<Character, MapBasedTrieNode> children;
    private int wordCount;

    public MapBasedTrieNode() {
        this.children = new HashMap<>(26);
        this.wordCount = 0;
    }

    @Override
    public Map<Character, MapBasedTrieNode> getChildren() {
        return this.children;
    }

    @Override
    public int getWordCount() {
        return this.wordCount;
    }

    @Override
    public void incrementWordCount() {
        this.wordCount++;
    }

    @Override
    public void decrementWordCount() {
        this.wordCount--;
    }
}
