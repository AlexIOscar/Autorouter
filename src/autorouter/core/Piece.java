package autorouter.core;
import elements.Element;

import java.util.HashSet;
import java.util.Set;

//представление детали
public class Piece {
    //тип металлопроката
    RollType rt;
    //основные габаритные размеры
    double length;
    double width;
    double height;


    // основное здесь - набор элементов, составляющих деталь

    Set<Element> elementList = new HashSet<>();
    public void addElement(Element element){
        elementList.add(element);
    }

    public void removeElement(Element el){
        elementList.remove(el);
    }

    public Set<Element> getElementList() {
        return elementList;
    }
}
