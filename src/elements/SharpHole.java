package elements;

import java.util.ArrayList;
import java.util.List;

//точное отверстие. получается, как правило, сверлением
public class SharpHole implements Element {
    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}
