import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatLineIndex {

    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        try {
            MyScanner sc = new MyScanner(new FileInputStream(args[0]));
            int j = 1;
            int i = 1;
            while (true) {
                String string = sc.getNextWord();
                if (string == null) {
                    break;
                }
                if (string == "\n") {
                    j++;
                    i = 1;
                } else {
                    string = string.toLowerCase();
                    if (map.containsKey(string)) {
                        map.get(string).set(0, map.get(string).get(0) + 1);
                        map.get(string).add(j);
                        map.get(string).add(i);
                        i++;
                    } else {
                        map.put(string, initialArray(i, j));
                        i++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("InputError" + e.getMessage());
        }

        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8");
            try {
                for (String e : map.keySet()) {
                    writer.write(e + " " + toString(map.get(e)) + "\n");
                }

            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Integer> initialArray(int i, int j) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(j);
        list.add(i);
        return list;
    }

    public static String toString(ArrayList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0) + " ");
        for (int i = 1; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else if (i % 2 != 0) {
                sb.append(list.get(i) + ":");
            } else {
                sb.append(list.get(i) + " ");
            }
        }
        return sb.toString();
    }
}