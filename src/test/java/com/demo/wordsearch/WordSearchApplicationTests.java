package com.demo.wordsearch;

import com.demo.wordsearch.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordSearchApplicationTests {

    @Test
    public void contextLoads() {
        WordSearchApplication.argsValue = new String[]{"upload-word.txt"};
    }


}
