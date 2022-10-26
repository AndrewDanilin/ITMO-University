package markup;

public class Text implements AllElements {
    private String s;

    public Text(String s) {
        this.s = s;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(s);
    }

    public void toHtml(StringBuilder sb) {
        sb.append(s);
    }

}
