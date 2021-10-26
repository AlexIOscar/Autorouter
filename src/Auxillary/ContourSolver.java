package Auxillary;

import Elements.Contour;
import CutCounterPkg.*;
import Plasm.PlasmPoint;

import java.util.*;
import java.util.stream.Collectors;

public class ContourSolver {

    public static boolean debugMode = true;

    public static boolean isConvexNGon(Contour contour) {

        //если контур не является многоугольником, сразу возвращаем false
        if (!isNGon(contour)) return false;

        //поскольку у точки типа PlasmPoint есть еще и такой костыль как "номер
        // контура-владельца", нужно сгруппировать все точки по их контурам.
        Map<Integer, List<PlasmPoint>> contours;
        contours = contour.getPointList().stream().collect(Collectors.groupingBy(x -> x.counterNum));

        //если в мапе оказалось число контуров, не равное 1, то деталь содержит внутренние окна, и
        // она точно не многоугольник, так что возвращаем false
        if (contours.size() != 1) return false;

        //получаем ключ единственного контура
        int contNum = contours.keySet().iterator().next();

        // вычислитель углов из библиотеки CutCounter работает со своим форматом точек (класс
        // Line.Point). А контур состоит из точек типа PlasmPoint. Необходимо сделать конверсию
        // в нужный тип, прежде чем с ним работать
        List<Line.Point> ccPList = contours.get(contNum).stream().map(w -> new Line.Point(w.x,
                w.y)).collect(Collectors.toList());

        //создаем лист линий, образующих многоугольник
        List<Line> lineList = new ArrayList<>();

        try {
            int nPoints = ccPList.size();
            for (int i = 0; i < nPoints - 1; i++) {
                lineList.add(new Line(ccPList.get(i), ccPList.get(i + 1)));
                //System.out.println(i);
            }
        } catch (WrongLineExeption wle) {
            wle.printDegPtErr();
        }

        List<Double> angleList = new ArrayList<>();
        for (int i = 0; i < lineList.size() - 1; i++) {
            angleList.add(Line.getFullAngle(lineList.get(i), lineList.get(i + 1)));
        }
        angleList.add(Line.getFullAngle(lineList.get(lineList.size() - 1), lineList.get(0)));

        if (debugMode) {
            System.out.println("Corners qty: " + angleList.size());
            System.out.println("Lines qty: " + lineList.size());

            for (double angle : angleList) {
                System.out.println(angle);
            }
        }

        for (int i = 0; i < angleList.size() - 1; ++i) {
            if ((angleList.get(i) < 0) != (angleList.get(i + 1)) < 0)
                return false;
        }
        return true;
    }

    public static boolean isNGon(Contour contour) {
        return contour.getPointList().stream().noneMatch(n -> n.rad != 0);
    }
}
