package elements;

import java.util.ArrayList;
import java.util.List;

// элемент "выруб"
public class Punch implements Element {
    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Butt.class);
    }
}
