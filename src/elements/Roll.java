package elements;

import java.util.ArrayList;
import java.util.List;

// Элемент "поверхность проката". Он всегда приходит уже "готовым" в состоянии поставки металла,
// и, соответственно, его лист предшественников пустой
public class Roll implements Element {
    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();
        //necessaryList is always empty for this element type
}
