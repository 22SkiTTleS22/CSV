import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyClass {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyClass.class);
    private static final String PATH = "./src/main/resources/sorted/";
    private static final String CSV_EXPANSION = ".csv";
    private static Comparator<String> comparator;

    public static File fileSort(File file, SortOrder sortOrder, int numberOfStringsForParts) throws IOException {
        LOGGER.debug("File sorting");
        try {
            setComparator(sortOrder);
        } catch (SortOrderFormatException ex) {
            LOGGER.error("Sort order is wrong! Sort order is descending", ex);
            comparator = Comparator.reverseOrder();
        }
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
            return mergeParts();
        }
    }

    private static void setComparator(SortOrder sortOrder) throws SortOrderFormatException {
        if (sortOrder == SortOrder.DESCENDING) {
            comparator = Comparator.reverseOrder();
        } else if (sortOrder == SortOrder.ASCENDING) {
            comparator = Comparator.naturalOrder();
        } else {
            throw new SortOrderFormatException("Wrong sorting order!");
        }
    }

    private static void writeListToFile(List<String> listOfStrings, int countOfFiles) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "file" + countOfFiles + CSV_EXPANSION))) {
            for (String elem : listOfStrings) {
                bw.write(elem + "\n");
            }
        }
    }

    private static File mergeParts() throws IOException {
        File dirForFiles = new File(PATH);
        File[] listOfSortedFiles = dirForFiles.listFiles();
        int countOfMergedFiles = 0;
        while (listOfSortedFiles.length > 1) {
            mergeTwoFiles(listOfSortedFiles[0], listOfSortedFiles[1], ++countOfMergedFiles);
            Files.delete(listOfSortedFiles[0].toPath());
            Files.delete(listOfSortedFiles[1].toPath());
            listOfSortedFiles = dirForFiles.listFiles();
        }
        return listOfSortedFiles[0];
    }

    private static void mergeTwoFiles(File file1, File file2, int countOfMergedFiles) throws IOException {
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