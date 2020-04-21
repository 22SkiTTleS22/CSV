import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestMyClass {
    private static final String PATH = ".\\src\\main\\resources\\";

    @Test
    public void testFileSort() {
        try {
            File file = new File(PATH+"data.csv");
            File methodResult = new File(PATH+"sorted\\"+MyClass.FilePartSort(file, Collections.reverseOrder(), 3));
            File result = new File(PATH+"result.csv");
            assertTrue("The files differ!",
                    FileUtils.contentEquals(methodResult, result));
            methodResult.delete();
        } catch (IOException ex) {
            fail();
        }
    }
}
