package ds.trie;

/**
 * Array based Trie for storing lowercase alphabets
 */
public class ArrayBasedTrieNode implements TrieNode<ArrayBasedTrieNode[]> {
    private final ArrayBasedTrieNode[] children;
    private int wordCount;

    public ArrayBasedTrieNode() {
        this.children = new ArrayBasedTrieNode[26];
        this.wordCount = 0;
    }

    @Override
    public ArrayBasedTrieNode[] getChildren() {
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
