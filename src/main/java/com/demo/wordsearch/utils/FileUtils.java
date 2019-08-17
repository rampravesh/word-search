package com.demo.wordsearch.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ResourceUtils;

public class FileUtils {

    public static List<String> getInputList(String fileName) throws IOException {
//        Resource banner = resourceLoader.getResource("file:data.txt");
        List<String> lines = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:" + fileName);
        lines = Files.readAllLines(file.toPath());
        lines.forEach(x -> {
            System.out.println(x);
        });
        return lines;
    }

//    public static void writeToTextFile(String fileName, String content) throws IOException {
//        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
//    }

}
