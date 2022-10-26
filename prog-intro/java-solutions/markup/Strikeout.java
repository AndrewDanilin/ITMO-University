package markup;

import java.util.List;

public class Strikeout extends AbstractElement {

    public Strikeout(List<AllElements> list) {
        super(list);
    }

    @Override
    public String getSymbolOfEnd() {
        return "~";
    }

    @Override
    public String getStartOfHtml() {
        return "<s>";
    }

    @Override
    public String getEndOfHtml() {
        return "</s>";
    }

}