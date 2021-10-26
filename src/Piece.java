import Elements.IElement;

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
    Set<IElement> elementList = new HashSet<>();

    public void addElement(IElement element){
        elementList.add(element);
    }

    public void removeElement(IElement el){
        elementList.remove(el);
    }
}
