package elements;

import java.util.ArrayList;
import java.util.List;

//Точное отверстие. Получается, как правило, сверлением
public class SharpHole implements Element {

    private double diameter;
    public List<Class<? extends Element>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends Element>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Contour.class);
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
}
