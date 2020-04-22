import java.io.*;
import java.util.*;

public class MyClass {

    private static final String PATH = ".\\src\\main\\resources\\sorted\\";

    public static String FilePartSort(File file, Comparator c, int n) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> listString = new ArrayList<>();
//            while ((row = csvReader.readLine()) != null) {
//                String[] data = row.split(",");
            String s = br.readLine();
            while (s != null) {
                while ((s != null) && (listString.size() != n)) {
                    listString.add(s);
                    s = br.readLine();
                }
                Collections.sort(listString, c);
                count++;
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "file" + count + ".csv"))) {
                    for (String str : listString) {
                        bw.write(str + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                listString.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return MergeParts(c);
    }

    private static String MergeParts(Comparator c) {
        File f = new File(PATH);
        String[] fileList = f.list();
        try {
            int count = 0;
            while (fileList.length > 1) {
                count++;
                try (BufferedReader br1 = new BufferedReader(new FileReader(PATH+fileList[0]));
                     BufferedReader br2 = new BufferedReader(new FileReader(PATH+fileList[1]));
                     BufferedWriter bw = new BufferedWriter(new FileWriter(PATH+"mergedfile" + count + ".csv"))) {
                    String str1 = br1.readLine();
                    String str2 = br2.readLine();
                    if (!c.equals(Collections.reverseOrder())) {
                        //по возрастанию
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
                                bw.write(str2);
                                str2 = br2.readLine();
                            }
                        } else {
                            while (str1 != null) {
                                bw.write(str1);
                                str1 = br1.readLine();
                            }
                        }
                    } else {
                        //по убыванию
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
                File f1 = new File(PATH+fileList[0]);
                f1.delete();
                f1 = new File(PATH+fileList[1]);
                f1.delete();
                fileList = f.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileList[0];
    }

}