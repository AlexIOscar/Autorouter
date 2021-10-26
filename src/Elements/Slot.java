package Elements;

import java.util.ArrayList;
import java.util.List;

// элемент "паз", фрезеруется
public class Slot implements IElement {
    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}
