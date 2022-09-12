package elements;

import java.util.ArrayList;
import java.util.List;

// элемент "технологическое отверстие", как правило режется термически
public class TechHole implements Element {
    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}
