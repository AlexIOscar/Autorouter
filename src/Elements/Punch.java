package Elements;

import java.util.ArrayList;
import java.util.List;

// элемент "выруб"
public class Punch implements IElement {
    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Butt.class);
    }
}
