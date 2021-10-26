import Elements.IElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// базовый построитель графа, более слабый чем ExtGraphBuilder, и при этом более сложный и менее
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
        for (IElement e0 : piece.elementList) {
            for (IElement e1 : piece.elementList) {
                if (checkNecessity(e0, e1)) {
                    graph.add(new GraphBranch(e1, e0));
                }
            }
        }
    }

    //добавить ветки графа в обе стороны
    @Deprecated
    private void addTwinBranches() {
        for (IElement e0 : piece.elementList) {
            for (IElement e1 : piece.elementList) {
                if (getLinkedKnots(e0, graph) == getLinkedKnots(e1, graph)) {
                    //нужны еще проверки какие-то, сейчас все работает через жопу
                    graph.add(new GraphBranch(e1, e0));
                    graph.add(new GraphBranch(e0, e1));
                }
            }
        }
    }

    protected boolean checkNecessity(IElement nessListOwner, IElement chekedElem) {
        // входит ли элемент chekedElem в лист потребностей элемента nessListOwner?
        Class<? extends IElement> class1 = chekedElem.getClass();
        List<Class<? extends IElement>> necessaryList = nessListOwner.getNecessaryList();
        for (Class<? extends IElement> el : necessaryList) {
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
        for (IElement el : piece.elementList) {
            checkCollideAndRemove(el);
        }
    }

    // метод проверяет, есть ли в "дереве нужд" el такой элемент, который одновременно есть и в
    // нуждах "первого поколения" (непосредственных) узла el
    private void checkCollideAndRemove(IElement el) {
        //лист "непосредственных нужд":
        List<IElement> nessList = getLinkedKnots(el, graph);

        //лист нужд "высших поколений", заполняем его, вызывая getNessTree для каждого элемента в
        // nessList:
        Set<IElement> collected = new HashSet<>();
        for (IElement nessEl : nessList) {
            getNessTree(nessEl, collected);
        }

        for (IElement elem : collected) {
            for (IElement el2 : nessList) {
                if (elem == el2) {
                    removeBranch(elem, el);
                }
            }
        }
    }

    // для элемента element все его нужды, включая нужды "высших поколений", помещаются в collected
    private void getNessTree(IElement element, Set<IElement> collected) {
        List<IElement> graphField = getLinkedKnots(element, graph);
        collected.addAll(graphField);
        for (IElement el : graphField
        ) {
            getNessTree(el, collected);
        }
    }

    // метод, возвращающий для элемента sender и графа gbList список элементов-нужд "первого
    // поколения"
    private List<IElement> getLinkedKnots(IElement sender, List<GraphBranch> gbList) {
        List<GraphBranch> grBr = getFilteredBranches(sender, gbList);
        List<IElement> upList = new ArrayList<>();
        for (GraphBranch gb : grBr) {
            upList.add(gb.getFromEl());
        }
        return upList;
    }

    //метод возвращает только те ветви графа graph, которые "указывают" на узел sender
    private List<GraphBranch> getFilteredBranches(IElement sender, List<GraphBranch> graph) {
        return graph.stream().filter(t -> t.getToEl() == sender).collect(Collectors.toList());
    }

    //удаляет из графа ветку, идущую от from к to
    private void removeBranch(IElement from, IElement to) {
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
