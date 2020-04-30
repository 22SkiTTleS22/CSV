import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;

public class TestMyClass {

    private static final String PATH = "./src/main/resources/";

    @BeforeClass
    public static void generateFile() throws IOException {
        RandomFile.generateFile(1000000, 5);
    }

    @Test
    public void testFileSortDescending() {
        try {
            File file = new File(PATH + "alphabet.csv");
            File methodResult = MyClass.fileSort(file, Comparator.reverseOrder(), 6);
            File result = new File(PATH + "alphabetDescending.csv");
            assertTrue("The files differ alphabetDescending!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileSortAscending() {
        try {
            File file = new File(PATH + "alphabet.csv");
            File methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 6);
            File result = new File(PATH + "alphabetAscending.csv");
            assertTrue("The files differ alphabetAscending!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileSortMultiple() {
        try {
            File file = new File(PATH + "data.csv");
            File methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 5);
            File result = new File(PATH + "result.csv");
            assertTrue("The files differ result Multiple!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileSortFewer() {
        try {
            File file = new File(PATH + "data.csv");
            File methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 20);
            File result = new File(PATH + "result.csv");
            assertTrue("The files differ result 2!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testFileSortBiggerNoMultiple() {
        try {
            File file = new File(PATH + "data.csv");
            File methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 4);
            File result = new File(PATH + "result.csv");
            assertTrue("The files differ result 3!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testBigFileSort() {
        try {
            File file = new File(PATH + "bigdata.csv");
            File methodResult = MyClass.fileSort(file, Collections.reverseOrder(), 10000);
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testEmptyFileSort() {
        try {
            File file = new File(PATH + "emptyFile.csv");
            File methodResult = MyClass.fileSort(file, Comparator.reverseOrder(), 6);
            File result = new File(PATH + "emptyFile.csv");
            assertTrue("The files differ emptyFile!", FileUtils.contentEquals(methodResult, result));
        } catch (IOException ex) {
            fail();
        }
    }

}
