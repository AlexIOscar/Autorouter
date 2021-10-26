import Elements.IElement;

// класс, представляющий ветку графа
public class GraphBranch {

    public IElement getFromEl() {
        return fromEl;
    }

    public IElement getToEl() {
        return toEl;
    }

    // элемент-начало ветви
    private final IElement fromEl;
    // элемент-окончание ветви
    private final IElement toEl;

    public GraphBranch(IElement fromEl, IElement toEl) {
        this.fromEl = fromEl;
        this.toEl = toEl;
    }

    public void printBranch() {
        System.out.println(fromEl.getClass().getName() + " -> " + toEl.getClass().getName());
    }
}
