import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class MyClass {

    private static final String PATH = "./src/main/resources/sorted/";
    private static final String CSV_EXPANSION = ".csv";

    public static File fileSort(File file, Comparator<String> comparator, int numberOfStringsForParts) throws IOException {
        if (file.length() == 0) {
            return file;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                int countOfFiles = 0;
                List<String> listOfStrings = new ArrayList<>();
                String str = br.readLine();
                while (str != null) {
                    while ((str != null) && (listOfStrings.size() != numberOfStringsForParts)) {
                        listOfStrings.add(str);
                        str = br.readLine();
                    }
                    Collections.sort(listOfStrings, comparator);
                    writeListToFile(listOfStrings, ++countOfFiles);
                    listOfStrings.clear();
                }
            }
            return mergeParts(comparator);
        }
    }

    private static void writeListToFile(List<String> listOfStrings, int countOfFiles) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "file" + countOfFiles + CSV_EXPANSION))) {
            for (String elem : listOfStrings) {
                bw.write(elem + "\n");
            }
        }
    }

    private static File mergeParts(Comparator comparator) throws IOException {
        File dirForFiles = new File(PATH);
        File[] listOfSortedFiles = dirForFiles.listFiles();
        int countOfMergedFiles = 0;
        while (listOfSortedFiles.length > 1) {
            mergeTwoFiles(listOfSortedFiles[0], listOfSortedFiles[1], ++countOfMergedFiles, comparator);
            Files.delete(listOfSortedFiles[0].toPath());
            Files.delete(listOfSortedFiles[1].toPath());
            listOfSortedFiles = dirForFiles.listFiles();
        }
        return listOfSortedFiles[0];
    }

    private static void mergeTwoFiles(File file1, File file2, int countOfMergedFiles, Comparator comparator) throws IOException {
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));
             BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "mergedfile" + countOfMergedFiles + CSV_EXPANSION))) {
            String str1 = br1.readLine();
            String str2 = br2.readLine();
            if (comparator.equals(Comparator.naturalOrder())) {
                while (str1 != null && str2 != null) {
                    if (str1.compareTo(str2) > 0) {
                        bw.write(str2 + "\n");
                        str2 = br2.readLine();
                    } else if (str1.compareTo(str2) < 0) {
                        bw.write(str1 + "\n");
                        str1 = br1.readLine();
                    } else {
                        bw.write(str1 + "\n" + str2 + "\n");
                        str1 = br1.readLine();
                        str2 = br2.readLine();
                    }
                }
            } else {
                while (str1 != null && str2 != null) {
                    if (str1.compareTo(str2) > 0) {
                        bw.write(str1 + "\n");
                        str1 = br1.readLine();
                    } else if (str1.compareTo(str2) < 0) {
                        bw.write(str2 + "\n");
                        str2 = br2.readLine();
                    } else {
                        bw.write(str1 + "\n" + str2 + "\n");
                        str1 = br1.readLine();
                        str2 = br2.readLine();
                    }
                }
            }
            if (str1 == null) {
                while (str2 != null) {
                    bw.write(str2 + "\n");
                    str2 = br2.readLine();
                }
            } else {
                while (str1 != null) {
                    bw.write(str1 + "\n");
                    str1 = br1.readLine();
                }
            }
        }
    }

}