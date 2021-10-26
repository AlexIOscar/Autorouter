package Elements;

import java.util.ArrayList;
import java.util.List;

//элемент "точный контур". сюда относятся всякие фрезерованные (мехобработанные) элементы контура
// детали (с особыми требованиями к точности)
public class SharpContour implements IElement {
    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}
