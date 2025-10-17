package ds.trie;

public interface TrieNode<T> {
    T getChildren();
    int getWordCount();
    void incrementWordCount();
    void decrementWordCount();
}
