package autorouter.core;

import elements.Element;

// класс, представляющий ветку графа
public class GraphBranch {

    public Element getFromEl() {
        return fromEl;
    }

    public Element getToEl() {
        return toEl;
    }

    // элемент-начало ветви
    private final Element fromEl;
    // элемент-окончание ветви
    private final Element toEl;

    public GraphBranch(Element fromEl, Element toEl) {
        this.fromEl = fromEl;
        this.toEl = toEl;
    }

    public void printBranch() {
        System.out.println(fromEl.getClass().getName() + " -> " + toEl.getClass().getName());
    }
}
