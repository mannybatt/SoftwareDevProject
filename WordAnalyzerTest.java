package WordAnalyzerPackage;

import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

class WordAnalyzerTest {

    //This Tests for a file containing Nothing at all
    @Test
    void analyzer_NoText() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "This file is EMPTY!\n\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }

    //This Tests for a file containing only a single number
    @Test
    void analyzer_text_1() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("1");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "No Words Found!\n\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }

    //This Tests for a file containing only a single decimal
    @Test
    void analyzer_text_1point1() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("1.1");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "No Words Found!\n\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }

    //This Tests for a file containing only a single letter
    @Test
    void analyzer_text_a() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("a");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "1.  a   \t\t\t1\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }

    //This Tests for a file containing only a single word
    @Test
    void analyzer_text_word() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("word");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "1.  word   \t\t\t1\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }

    //This Tests for a file containing one word, twice
    @Test
    void analyzer_text_wordword() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("word word");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "1.  word   \t\t\t2\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }

    //This Tests for a file containing only a single non-alphanumerical char
    @Test
    void analyzer_text_specialChar() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("#");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "No Words Found!\n\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }

    //This Tests for a file containing a mix of words and special chars
    @Test
    void analyzer_text_specialCharwithWords() throws IOException {
        String fileName = "TestText.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("# some @ words $");
        printWriter.close();

        WordAnalyzer wa = new WordAnalyzer();
        String toCompare = wa.analyzer(file);
        String expectedAnswer = "1.  some   \t\t\t1\n2.  words   \t\t\t1\n";
        assertEquals(expectedAnswer, toCompare);

        file.delete();
    }
}