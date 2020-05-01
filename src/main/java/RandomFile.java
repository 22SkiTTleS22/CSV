import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class RandomFile {

    private final static Logger LOGGER = LoggerFactory.getLogger(RandomFile.class);
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String PATH = "./src/main/resources/";

    private static String generate(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    public static void generateFile(int numberOfStrings, int stringLength) {
        LOGGER.debug("Generate file");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "bigdata.csv"))) {
            for (int i = 1; i <= numberOfStrings; ++i) {
                bw.write(generate(stringLength) + "\n");
            }
        } catch (IOException ex) {
            LOGGER.error("Can't generate file", ex);
        }
    }
}