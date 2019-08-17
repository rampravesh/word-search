package com.demo.wordsearch.services;

import com.demo.wordsearch.model.TrieNode;

import java.util.List;

/**
 * Trie data structure service to do menupulation operation from data.
 */
public interface TrieService {
    public void insert(String work);

    boolean delete(String word);

    boolean containsNode(String word);

    List<String> getContainsWords(String word);

    public TrieNode searchNode(String str);

    boolean isEmpty();

}
