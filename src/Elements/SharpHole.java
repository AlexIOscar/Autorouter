package Elements;

import java.util.ArrayList;
import java.util.List;

//точное отверстие. получается, как правило, сверлением
public class SharpHole implements IElement {
    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }
}
