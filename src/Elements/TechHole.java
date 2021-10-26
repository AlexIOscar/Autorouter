package Elements;

import java.util.ArrayList;
import java.util.List;

// элемент "технологическое отверстие", как правило режется термически
public class TechHole implements IElement {
    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}
