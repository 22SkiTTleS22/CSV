import java.io.*;
import java.util.*;

class MyClass {

    private static final String PATH = ".\\src\\main\\resources\\sorted\\";

    static String FileSort(File file, Comparator comparator, int numberOfStringsForParts) {
        int countOfFiles = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> listOfStrings = new ArrayList<>();
            String str = br.readLine();
            if (str == null) {
                throw new Exception("Empty file");
            }
            while (str != null) {
                while ((str != null) && (listOfStrings.size() != numberOfStringsForParts)) {
                    listOfStrings.add(str);
                    str = br.readLine();
                }
                Collections.sort(listOfStrings, comparator);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "file" + ++countOfFiles + ".csv"))) {
                    for (String elem : listOfStrings) {
                        bw.write(elem + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                listOfStrings.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MergeParts(comparator);
    }

    private static String MergeParts(Comparator comparator) {
        File dirForFiles = new File(PATH);
        String[] listOfSortedFiles = dirForFiles.list();
        try {
            int countOfMergedFiles = 0;
            while (listOfSortedFiles.length > 1) {
                try (BufferedReader br1 = new BufferedReader(new FileReader(PATH+listOfSortedFiles[0]));
                     BufferedReader br2 = new BufferedReader(new FileReader(PATH+listOfSortedFiles[1]));
                     BufferedWriter bw = new BufferedWriter(new FileWriter(PATH+"mergedfile" + ++countOfMergedFiles + ".csv"))) {
                    String str1 = br1.readLine();
                    String str2 = br2.readLine();
                    if (comparator.equals(Comparator.naturalOrder())) {
                        while (str1 != null && str2 != null) {
                            if (str1.compareTo(str2) > 0) {
                                bw.write(str2 + "\n");
                                str2 = br2.readLine();
                            } else if (str1.compareTo(str2) == 0) {
                                bw.write(str1 + "\n");
                                str1 = br1.readLine();
                                bw.write(str2 + "\n");
                                str2 = br2.readLine();
                            } else if (str1.compareTo(str2) < 0) {
                                bw.write(str1 + "\n");
                                str1 = br1.readLine();
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
                    } else {
                        while (str1 != null && str2 != null) {
                            if (str1.compareTo(str2) > 0) {
                                bw.write(str1 + "\n");
                                str1 = br1.readLine();
                            } else if (str1.compareTo(str2) == 0) {
                                bw.write(str1 + "\n");
                                str1 = br1.readLine();
                                bw.write(str2 + "\n");
                                str2 = br2.readLine();
                            } else if (str1.compareTo(str2) < 0) {
                                bw.write(str2 + "\n");
                                str2 = br2.readLine();
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                File f1 = new File(PATH+listOfSortedFiles[0]);
                if (!f1.delete()) {
                    throw new Exception("File not found");
                }
                f1 = new File(PATH+listOfSortedFiles[1]);
                if (!f1.delete()) {
                    throw new Exception("File not found");
                }
                listOfSortedFiles = dirForFiles.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfSortedFiles[0];
    }

}