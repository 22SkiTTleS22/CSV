import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class RandomFile {

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
        RandomFile randomFile = new RandomFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "bigdata.csv"))) {
            for (int i = 1; i <= numberOfStrings; ++i) {
                bw.write(randomFile.generate(stringLength) + "\n");
                bw.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}