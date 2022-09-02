package elements;

import java.util.ArrayList;
import java.util.List;

// элемент, называемый обычно "скос"
public class SkewPunch implements Element {
    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Butt.class);
    }
}
