import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;

public class TestMyClass {

    private static final String PATH = "./src/main/resources/";

    @Test
    public void testFileSort() {
        try {
            //по убыванию
            File file = new File(PATH + "alphabet.csv");
            File methodResult = MyClass.fileSort(file, Comparator.reverseOrder(), 6);
            File result = new File(PATH + "alphabetDescending.csv");
            assertTrue("The files differ alphabetDescending!", FileUtils.contentEquals(methodResult, result));
            if (!methodResult.delete()) {
                throw new FileNotFoundException("File not found");
            }
            //по возрастанию
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 6);
            result = new File(PATH + "alphabetAscending.csv");
            assertTrue("The files differ alphabetAscending!", FileUtils.contentEquals(methodResult, result));
            if (!methodResult.delete()) {
                throw new FileNotFoundException("File not found");
            }
            //когда строк в сортируемом файле кратное numberOfStringsForParts;
            file = new File(PATH + "data.csv");
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 5);
            result = new File(PATH + "result.csv");
            assertTrue("The files differ result 1!", FileUtils.contentEquals(methodResult, result));
            if (!methodResult.delete()) {
                throw new FileNotFoundException("File not found");
            }
            //строк в сортируемом файле меньше, чем numberOfStringsForParts;
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 20);
            assertTrue("The files differ result 2!", FileUtils.contentEquals(methodResult, result));
            if (!methodResult.delete()) {
                throw new FileNotFoundException("File not found");
            }
            //строк в файле больше numberOfStringsForParts и не кратное numberOfStringsForParts.
            methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 4);
            assertTrue("The files differ result 3!", FileUtils.contentEquals(methodResult, result));
            if (!methodResult.delete()) {
                throw new FileNotFoundException("File not found");
            }
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testBigFileSort() {
        RandomFile.generateFile(1000000, 5);
        File file = new File(PATH + "bigdata.csv");
        try {
            File methodResult = MyClass.fileSort(file, Collections.reverseOrder(), 10000);
            if (!methodResult.delete()) {
                throw new FileNotFoundException("File not found");
            }
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void testEmptyFileSort() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "emptyFile" + ".csv"));
            File file = new File(PATH + "emptyFile.csv");
            File methodResult = MyClass.fileSort(file, Comparator.naturalOrder(), 3);
            Assert.fail("Expected EmptyFileException");
        } catch (EmptyFileException ex) {
            Assert.assertEquals("File is empty", ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
