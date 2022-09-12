package elements;

import java.util.ArrayList;
import java.util.List;

// класс представляющий элемент "фаска"
public class Bevel implements Element, Thermocutable {

    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}