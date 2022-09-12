package elements;

import java.util.ArrayList;
import java.util.List;

//элемент "точный контур". сюда относятся всякие фрезерованные (мехобработанные) элементы контура
// детали (с особыми требованиями к точности)
public class SharpContour implements Element {
    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}
