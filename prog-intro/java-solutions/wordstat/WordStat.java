import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class WordStat {
    public static void main(String[] args) {
        try {
            Reader reader = new FileReader("input.txt");
                try {
                    Map<StringBuilder, Integer> wordCount = new HashMap<>();
                    StringBuilder sb = new StringBuilder();
                    while (true){
                        int ch = reader.read();
                        if (ch == -1){
                            break;
                        }
                        if (Character.isUnicodeIdentifierPart((char) ch) && !Character.isWhitespace((char) ch)){
                            sb.append((char) ch);
                        } else {
                            if (!wordCount.containsKey(sb)){
                                wordCount.put(sb, 1);
                            } else {
                                wordCount.put(sb, wordCount.get(sb)+1);
                            }
                            sb.setLength(0);
                        }
                    }
                    for (StringBuilder word: wordCount.keySet()) {
                        System.out.println("Word " + word + " occurred " + wordCount.get(word) + " times");
                    }
                } finally {
                    reader.close();
                }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
       

    }
}
