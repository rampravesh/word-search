package com.demo.wordsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class WordSearchApplication {
    public static String[] argsValue;

    public static void main(String[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            args = new String[]{"upload-word.txt"};
        }
        System.out.println("Uploading file >>>>>>>>> " + args[0]);
        argsValue = args;
        SpringApplication.run(WordSearchApplication.class, args);
    }

}
