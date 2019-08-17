package com.demo.wordsearch.services;

import com.demo.wordsearch.WordSearchApplication;
import com.demo.wordsearch.model.TrieNode;
import com.demo.wordsearch.utils.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class TrieServiceImpl implements TrieService {
    private TrieNode root = new TrieNode();


    @PostConstruct
    public void init() throws IOException {
        List<String> inputList = FileUtils.getInputList(WordSearchApplication.argsValue[0]);
        for (String inputLine : inputList) {
            this.insertLine(inputLine.split(" "));
        }
    }


    @Override
    public void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    @Override
    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    @Override
    public boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    @Override
    public List<String> getContainsWords(String word) {
        TrieNode current = root;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return list;
            }
            current = node;
        }
        Deque<TrieNode> DQ = new ArrayDeque<TrieNode>();
        DQ.addLast(current);
        String result = word;
        while (!DQ.isEmpty()) {
            TrieNode first = DQ.removeFirst();
            if (first.isEndOfWord()) {
                list.add(result);
                result = word;
            }

            if (first.getChildren().keySet().toArray().length > 0)
                result += first.getChildren().keySet().toArray()[0];

            for (Map.Entry<Character, TrieNode> entry : first.getChildren().entrySet()) {
                if (entry != null) {
                    DQ.add(entry.getValue());
                }
            }
        }
        //getResultRecusive(current, word, list);
        return list;
    }

    @Override
    public TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.getChildren();
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.getChildren();
            } else {
                return null;
            }
        }

        return t;
    }


    @Override
    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }


    private void insertLine(String[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            this.insert(inputArray[i]);
        }
    }


}
