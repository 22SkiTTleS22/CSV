//import org.junit.Rule;
import org.junit.Test;
//import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestMyClass {
//    @Rule
//    public TemporaryFolder folder = new TemporaryFolder();
    private static final String PATH = "E:\\TT\\CSV\\src\\main\\resources\\";

    @Test
    public void testFileSort() {
        try {
            //File file = folder.newFile("E:\\TT\\CSV\\src\\main\\resources\\data.txt");
            File file = new File(PATH+"data.txt");
            File methodResult = new File(PATH+"\\sorted\\"+MyClass.FilePartSort(file, Collections.reverseOrder(), 3));
            File result = new File(PATH+"result.txt");
            assertTrue("The files differ!",
                    FileUtils.contentEquals(methodResult, result));
            methodResult.delete();
        } catch (IOException ex) {
            fail();
        }
    }
}
