package com.demo.wordsearch.controller;

import com.demo.wordsearch.services.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class WordTestController {

    @Autowired
    TrieService trieService;

    @GetMapping(value = "/api/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> checkWordPresent(@RequestParam(required = true) String word) {
        return Arrays.asList(word + " search word is present :" + trieService.containsNode(word));
    }

    @GetMapping(value = "/api/words", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMatchingWords(@RequestParam(required = true) String word) {
        return trieService.getContainsWords(word);
    }

    @RequestMapping(value = "/api/words", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> deleteWrod(@RequestParam(required = true) String word) {
        return Arrays.asList(word + " word deleted succesfull:" + trieService.delete(word));
    }

    @RequestMapping(value = "/api/words", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> addNewWord(@RequestParam(required = true) String word) {
        if (Objects.isNull(word))
            return Arrays.asList(word + " invalid word charactor");
        trieService.insert(word);
        return Arrays.asList(word + " added to word dictionary:");
    }


}
