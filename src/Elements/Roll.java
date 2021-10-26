package Elements;

import java.util.ArrayList;
import java.util.List;

// элемент "поверхность проката". он всегда приходит уже "готовым" в состоянии поставки металла,
// и, соответственно, его лист предшественников пустой
public class Roll implements IElement{
    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

        //necessaryList is empty in this element type
}
