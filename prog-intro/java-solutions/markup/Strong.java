package markup;

import java.util.List;

public class Strong extends AbstractElement {

    public Strong(List<AllElements> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb);
    }

    @Override
    public String getSymbolOfEnd() {
        return "__";
    }

    @Override
    public String getStartOfHtml() {
        return "<strong>";
    }

    @Override
    public String getEndOfHtml() {
        return "</strong>";
    }

}