import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class RandomString {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String PATH = ".\\src\\main\\resources\\";

    public static String generate(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
    public static void generateFile() {
        RandomString randomString = new RandomString();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "bigdata.csv"))) {
            for (int i = 1; i <= 1000000; i++) {
                bw.write(randomString.generate(5) + "\n");
                bw.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}