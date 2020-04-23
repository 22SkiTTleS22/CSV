import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;

public class TestMyClass {

    private static final String PATH = ".\\src\\main\\resources\\";

    @Test
    public void testFileSort() {
        try {
            File file = new File(PATH + "data.csv");
            File methodResult = new File(PATH + "sorted\\" + MyClass.FileSort(file, Comparator.reverseOrder(), 3));
            File result = new File(PATH + "resultDescending.csv");
            assertTrue("The files differ!", FileUtils.contentEquals(methodResult, result));
            if (!methodResult.delete()) {
                throw new Exception("File not found");
            }
            methodResult = new File(PATH + "sorted\\" + MyClass.FileSort(file, Comparator.naturalOrder(), 3));
            result = new File(PATH + "resultAscending.csv");
            assertTrue("The files differ!", FileUtils.contentEquals(methodResult, result));
            if (!methodResult.delete()) {
                throw new Exception("File not found");
            }
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testBigFileSort() {
        try {
            RandomFile.generateFile(1000000, 5);
            File file = new File(PATH + "bigdata.csv");
            File methodResult = new File(PATH + "sorted\\" + MyClass.FileSort(file, Collections.reverseOrder(), 10000));
            if (!methodResult.delete()) {
                throw new Exception("File not found");
            }
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testEmptyFileSort() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "emptyFile" + ".csv"));
            bw.close();
            File file = new File(PATH + "emptyFile.csv");
            File methodResult = new File(PATH + "sorted\\" + MyClass.FileSort(file, Comparator.naturalOrder(), 3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
