package elements;

import java.util.List;

//базовый интерфейс элемента
public interface Element {

    // возврат набора классов элементов, которые могут быть его
    // непосредственными предшественниками
    List<Class<? extends Element>> getNecessaryList();
}
