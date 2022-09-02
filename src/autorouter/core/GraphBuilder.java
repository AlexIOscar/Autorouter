package autorouter.core;

import elements.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// базовый построитель графа, более слабый чем autorouter.core.ExtGraphBuilder, и при этом более сложный и менее
// логичный.
public class GraphBuilder {

    protected final Piece piece;
    protected final List<GraphBranch> graph = new ArrayList<>();

    public GraphBuilder(Piece piece) {
        this.piece = piece;
    }

    public void generateGraph() {
        this.addMonoBranches();
        this.removeBranches();
    }

    private void addMonoBranches() {
        for (Element e0 : piece.elementList) {
            for (Element e1 : piece.elementList) {
                if (checkNecessity(e0, e1)) {
                    graph.add(new GraphBranch(e1, e0));
                }
            }
        }
    }

    //добавить ветки графа в обе стороны
    @Deprecated
    private void addTwinBranches() {
        for (Element e0 : piece.elementList) {
            for (Element e1 : piece.elementList) {
                if (getLinkedKnots(e0, graph) == getLinkedKnots(e1, graph)) {
                    //нужны еще проверки какие-то, сейчас все работает через жопу
                    graph.add(new GraphBranch(e1, e0));
                    graph.add(new GraphBranch(e0, e1));
                }
            }
        }
    }

    protected boolean checkNecessity(Element nessListOwner, Element chekedElem) {
        // входит ли элемент chekedElem в лист потребностей элемента nessListOwner?
        Class<? extends Element> class1 = chekedElem.getClass();
        List<Class<? extends Element>> necessaryList = nessListOwner.getNecessaryList();
        for (Class<? extends Element> el : necessaryList) {
            if (class1 == el) return true;
        }
        return false;
    }

    public void printGraph() {
        for (GraphBranch gb : graph
        ) {
            gb.printBranch();
        }
    }

    private void removeBranches() {
        for (Element el : piece.elementList) {
            checkCollideAndRemove(el);
        }
    }

    // метод проверяет, есть ли в "дереве нужд" el такой элемент, который одновременно есть и в
    // нуждах "первого поколения" (непосредственных) узла el
    private void checkCollideAndRemove(Element el) {
        //лист "непосредственных нужд":
        List<Element> nessList = getLinkedKnots(el, graph);

        //лист нужд "высших поколений", заполняем его, вызывая getNessTree для каждого элемента в
        // nessList:
        Set<Element> collected = new HashSet<>();
        for (Element nessEl : nessList) {
            getNessTree(nessEl, collected);
        }

        for (Element elem : collected) {
            for (Element el2 : nessList) {
                if (elem == el2) {
                    removeBranch(elem, el);
                }
            }
        }
    }

    // для элемента element все его нужды, включая нужды "высших поколений", помещаются в collected
    private void getNessTree(Element element, Set<Element> collected) {
        List<Element> graphField = getLinkedKnots(element, graph);
        collected.addAll(graphField);
        for (Element el : graphField
        ) {
            getNessTree(el, collected);
        }
    }

    // метод, возвращающий для элемента sender и графа gbList список элементов-нужд "первого
    // поколения"
    private List<Element> getLinkedKnots(Element sender, List<GraphBranch> gbList) {
        List<GraphBranch> grBr = getFilteredBranches(sender, gbList);
        List<Element> upList = new ArrayList<>();
        for (GraphBranch gb : grBr) {
            upList.add(gb.getFromEl());
        }
        return upList;
    }

    //метод возвращает только те ветви графа graph, которые "указывают" на узел sender
    private List<GraphBranch> getFilteredBranches(Element sender, List<GraphBranch> graph) {
        return graph.stream().filter(t -> t.getToEl() == sender).collect(Collectors.toList());
    }

    //удаляет из графа ветку, идущую от from к to
    private void removeBranch(Element from, Element to) {
        List<Integer> indexesForRemove = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getFromEl() == from && graph.get(i).getToEl() == to) {
                //помещаем на нулевую позицию, вытесняя все накопленное вперед, т.о. лист будет
                // реверсивный относительно хронологии поступления в него элементов
                indexesForRemove.add(0, i);
            }
        }

        for (Integer integer : indexesForRemove) {
            graph.remove((int) integer);
        }
    }
}
