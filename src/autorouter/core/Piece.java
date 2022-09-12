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

    // Элементы объединяются в группы, поскольку часто необходимо обрабатывать их как единое целое. Например, в
    // расчетах норм времени.
    Set<Set<? extends Element>> bundleSet = new HashSet<>();

    public Set<Element> getElementList() {
        return elementList;
    }

    public Set<Set<? extends Element>> getBundleSet() {
        return bundleSet;
    }

    public void addElement(Element element){
        elementList.add(element);
    }

    public void removeElement(Element el){
        elementList.remove(el);
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
