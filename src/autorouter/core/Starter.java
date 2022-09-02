package autorouter.core;

import elements.*;

//класс-запускатор, тестируем всё здесь
public class Starter {
    public static void main(String[] args) {

        Piece piece1 = new Piece();
        piece1.addElement(new SharpHole());
        piece1.addElement(new Contour());
        piece1.addElement(new Roll());
        piece1.addElement(new SharpHole());
        piece1.addElement(new Slot());
        piece1.addElement(new Bend());

        GraphBuilder gb = new ExtGraphBuilder(piece1);
        gb.generateGraph();
        System.out.println(gb.graph.size());
        gb.printGraph();

/*
 Contour testCont = new Contour();
 List<PlasmPoint> lpp = new ArrayList<>();
 lpp.add(new PlasmPoint(0f,0f,0f,0));
 lpp.add(new PlasmPoint(100f,0f,0f,0));
 lpp.add(new PlasmPoint(300f,300f,0f,0));
 lpp.add(new PlasmPoint(60f,30f,0f,0));
 lpp.add(new PlasmPoint(60f,60f,0f,0));
 lpp.add(new PlasmPoint(0f,60f,0f,0));
 lpp.add(new PlasmPoint(0f,0f,0f,0));

 testCont.setPointList(lpp);
 System.out.println(ContourSolver.isConvexNGon(testCont));
 */
    }
}