package markup;

import java.util.List;

public class Paragraph {

    List<AllElements> list;

    public Paragraph(List<AllElements> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder sb) {
        for (AllElements element : list) {
            element.toMarkdown(sb);
        }
    }

    public void toHtml(StringBuilder sb) {
        for (AllElements element : list) {
            element.toHtml(sb);
        }
    }

}
