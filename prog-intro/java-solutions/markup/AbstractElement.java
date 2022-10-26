package markup;

import java.util.List;

public abstract class AbstractElement implements AllElements {
    private List<AllElements> list;
    private String markdownTag;
    private String openHtmlTag;
    private String closeHtmlTag;

    public AbstractElement(List<AllElements> list, String markdownTag, String openHtmlTag, String closeHtmlTag) {
        this.list = list;
        this.markdownTag = markdownTag;
        this.openHtmlTag = openHtmlTag;
        this.closeHtmlTag = closeHtmlTag;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownTag);
        for (AllElements element : list) {
            element.toMarkdown(sb);
        }
        sb.append(markdownTag);
    }
    
    public void toHtml(StringBuilder sb) {
        sb.append(openHtmlTag);
        for (AllElements element : list) {
            element.toHtml(sb);
        }
        sb.append(closeHtmlTag);
    }

}
