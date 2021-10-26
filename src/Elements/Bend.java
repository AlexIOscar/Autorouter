package Elements;

import java.util.ArrayList;
import java.util.List;

// класс элемента "изгиб"
public class Bend implements IElement {

    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
        necessaryList.add(SharpHole.class);
        necessaryList.add(TechHole.class);
        necessaryList.add(Slot.class);
        necessaryList.add(Butt.class);
        necessaryList.add(Mark.class);
    }
}
