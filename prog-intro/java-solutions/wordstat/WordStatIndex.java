import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatIndex {

    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        try {
            MyScanner read = new MyScanner(new FileInputStream(args[0]));
            int j = 1;
            while (true) {
                String string = read.getNextWord();
                if (string == null) {
                    break;
                }
                string = string.toLowerCase();
                if (map.containsKey(string)) {
                    map.get(string).set(0, map.get(string).get(0) + 1);
                    map.get(string).add(j);
                    j++;
                } else {
                    map.put(string, initialArray(j));
                    j++;
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    public static ArrayList initialArray(int j) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(j);
        return list;
    }

    public static String toString(ArrayList list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i) + " ");
            }
        }
	  return sb.toString();
    }


}
