package com.demo.wordsearch;

import com.demo.wordsearch.utils.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class ControllerAppTest {
    @Test
    public void uploadFileTest() {
        try {
            List<String> inputList = FileUtils.getInputList("upload-word.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
