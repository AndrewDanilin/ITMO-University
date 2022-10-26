package markup;

import java.util.List;

public class Emphasis extends AbstractElement {

    public Emphasis(List<AllElements> list) {
        super(list, "*", "<em>", "</em>");
    }

}