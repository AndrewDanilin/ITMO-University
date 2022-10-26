import java.io.*;

public class MyScanner {
    private BufferedReader br;
    private InputStream input;
    private String str;
    private FileInputStream name;
    private int inp;

    public MyScanner(InputStream input) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(input));
    }


    public MyScanner(String str) throws IOException {
        this(new ByteArrayInputStream(str.getBytes()));
    }


    public MyScanner(FileInputStream name) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(name, "UTF-8"));
    }


    public String getNextWord() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int inp = br.read();
            if (inp == -1) {
                br.close();
                return null;
            } else {
                char ch = (char) inp;
                if (Character.isLetter(ch) || Character.getType(ch) == 20 || ch == '\'') {
                    sb.append(ch);
                } else if (sb.length() > 0) {
                    String str = sb.toString();
                    sb.setLength(0);
                    return str;
                }
            }
        }
    }

    public boolean isN() {
        if ((char) this.inp == '\n') {
            return true;
        } else {
            return false;
        }
    }

    public String getNextSymbols() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            inp = br.read();
            if (inp == -1) {
                br.close();
                return null;
            }
            char ch = (char) inp;
            if (Character.isWhitespace(ch)) {
                if (ch == '\n') {
                    sb.append("");
                    return sb.toString();
                } else {
                    if (sb.length() > 0) {
                        return sb.toString();
                    }
                }

            } else {
                sb.append(ch);
            }
        }
    }


    public int countNumbers() throws IOException {
        StringBuilder sb = new StringBuilder();
        int number = 0;
        while (true) {
            int inp = br.read();
            if (inp == -1) {
                br.close();
                break;
            } else if (Character.isWhitespace((char) inp) && sb.length() > 0) {
                sb.setLength(0);
                number++;
            } else if (Character.isDigit((char) inp)) {
                sb.append((char) inp);
            }
        }
        return number;
    }

    public int nextInt() throws IOException {
        int number;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int inp = br.read();
            if (Character.isWhitespace((char) inp) || inp == -1) {
                if (sb.length() > 0) {
                    number = Integer.parseInt(sb.toString());
                    sb.setLength(0);
                    return number;
                }
            } else {
                sb.append((char) inp);
            }
        }
    }
}
