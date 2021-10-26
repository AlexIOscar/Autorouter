package Elements;

import Plasm.PlasmPoint;

import java.util.ArrayList;
import java.util.List;

//элемент "контур"
public class Contour implements IElement {

    public void setPointList(List<PlasmPoint> pointList) {
        this.pointList = pointList;
    }

    public List<PlasmPoint> getPointList() {
        return pointList;
    }

    private List<PlasmPoint> pointList = new ArrayList<>();

    public List<Class<? extends IElement>> getNecessaryList() {
        return necessaryList;
    }

    private static final List<Class<? extends IElement>> necessaryList = new ArrayList<>();

    static {
        necessaryList.add(Roll.class);
    }
}
