import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;

public class TestMyClass {

    private static final String PATH = "./src/main/resources/";
    private static final String FAIL_MESSAGE_FILESORT = "Exception with fileSort method";
    private static final String FAIL_MESSAGE_FILES_PROCESSING = "Exception with files processing";

    @BeforeClass
    public static void generateFile() throws IOException {
        RandomFile.generateFile(1000000, 5);
    }

    @Test
    public void testFileSortDescending() {
        try {
            File file = new File(PATH + "alphabet.csv");
            File methodResult = null;
            try {
                methodResult = MyClass.fileSort(file, Comparator.reverseOrder(), 6);
            } catch (IOException ex) {
                fail(FAIL_MESSAGE_FILESORT);
            }
            File result = new File(PATH + "alphabetDescending.csv");
            assertTrue("The files differ alphabetDescending!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail(FAIL_MESSAGE_FILES_PROCESSING);
        }
    }

    @Test
    public void testFileSortAscending() {
        try {
            File file = new File(PATH + "alphabet.csv");
            File methodResult = null;
            try {
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 6);
            } catch (IOException ex) {
                fail(FAIL_MESSAGE_FILESORT);
            }
            File result = new File(PATH + "alphabetAscending.csv");
            assertTrue("The files differ alphabetAscending!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail(FAIL_MESSAGE_FILES_PROCESSING);
        }
    }

    @Test
    public void testFileSortMultiple() {
        try {
            File file = new File(PATH + "data.csv");
            File methodResult = null;
            try {
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 5);
            } catch (Exception ex) {
                fail(FAIL_MESSAGE_FILESORT);
            }
            File result = new File(PATH + "result.csv");
            assertTrue("The files differ result Multiple!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail(FAIL_MESSAGE_FILES_PROCESSING);
        }
    }

    @Test
    public void testFileSortFewer() {
        try {
            File file = new File(PATH + "data.csv");
            File methodResult = null;
            try {
                methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 20);
            } catch (IOException ex) {
                fail(FAIL_MESSAGE_FILESORT);
            }
            File result = new File(PATH + "result.csv");
            assertTrue("The files differ result Fewer!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail(FAIL_MESSAGE_FILES_PROCESSING);
        }
    }

    @Test
    public void testFileSortBiggerNoMultiple() {
        try {
            File file = new File(PATH + "data.csv");
            File methodResult = null;
            try {
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 4);
            } catch (IOException ex) {
                fail(FAIL_MESSAGE_FILESORT);
            }
            File result = new File(PATH + "result.csv");
            assertTrue("The files differ result BiggerNoMultiple!", FileUtils.contentEquals(methodResult, result));
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail(FAIL_MESSAGE_FILES_PROCESSING);
        }
    }

    @Test
    public void testBigFileSort() {
        try {
            File file = new File(PATH + "bigdata.csv");
            File methodResult = null;
            try {
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 10000);
            } catch (IOException ex) {
                fail(FAIL_MESSAGE_FILESORT);
            }
            Files.delete(methodResult.toPath());
        } catch (IOException ex) {
            fail(FAIL_MESSAGE_FILES_PROCESSING);
        }
    }

    @Test
    public void testEmptyFileSort() {
        try {
            File file = new File(PATH + "emptyFile.csv");
            File methodResult = null;
            try {
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 6);
            } catch (IOException ex) {
                fail(FAIL_MESSAGE_FILESORT);
            }
            assertTrue("The files differ emptyFile!", FileUtils.contentEquals(methodResult, file));
        } catch (IOException ex) {
            fail(FAIL_MESSAGE_FILES_PROCESSING);
        }
    }

}