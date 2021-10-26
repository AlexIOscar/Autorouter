package Elements;

import java.util.List;

//базовый интерфейс элемента
public interface IElement {

    // возврат набора классов элементов, которые могут быть его
    // непосредственными предшественниками
    List<Class<? extends IElement>> getNecessaryList();
}
