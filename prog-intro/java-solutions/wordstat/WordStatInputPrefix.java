import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInputPrefix {

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            MyScanner read = new MyScanner(new FileInputStream(args[0]));
            while (true) {
                String string = read.getNextWord();
                if (string == null) {
                    break;
                }
                string = string.toLowerCase();
                if (string.length() > 2) {
                    String str = string.substring(0, 3);
                    if (map.containsKey(str)) {
                        map.put(str, map.get(str) + 1);
                    } else {
                        map.put(str, 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8");
            try {
                for (String e : map.keySet()) {
                    writer.write(e + " " + map.get(e) + "\n");
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}