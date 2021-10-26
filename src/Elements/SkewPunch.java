package Elements;

import java.util.ArrayList;
import java.util.List;

// элемент, называемый обычно "скос"
public class SkewPunch implements IElement {
    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Butt.class);
    }
}
