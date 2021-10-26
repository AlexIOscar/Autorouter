import Elements.IElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// расширение базового класса GraphBuilder. здесь реализована более "умная" и логичная модель
// построения графа
public class ExtGraphBuilder extends GraphBuilder {

    public ExtGraphBuilder(Piece piece) {
        super(piece);
    }

    //переопределенный вариант метода-генератора графа.
    // Последовательно проходим все возможные пары элементов и вызываем для них генератор ветвей
    // generateBranchesForTwin
    @Override
    public void generateGraph() {
        List<IElement> localElList = new ArrayList<>(piece.elementList);
        for (int i = 0; i < localElList.size(); i++) {
            for (int j = i + 1; j < localElList.size(); j++) {
                generateBranchesForTwin(localElList.get(i), localElList.get(j));
            }
        }
    }

    // метод "рассматривает" передаваемую ему произвольную пару элементов на предмет необходимости
    // построения между ними ребер графа. Собственно, исходов его работы может быть четыре: не
    // добавлено ни одного ребра, добавлены ребра в обоих направлениях, добавлено ребро только
    // el1 -> el2, либо только el2 -> el1
    private void generateBranchesForTwin(IElement el1, IElement el2) {
        if (checkNessRelation(el2, el1)) graph.add(new GraphBranch(el2, el1));
        if (checkNessRelation(el1, el2)) graph.add(new GraphBranch(el1, el2));
    }

    // метод проверяет два элемента на предмет необходимости построения ребра от from к to. Если
    // ребро провести нужно, то возврат true, если ребро не нужно - то false. Вся суть анализа -
    // проверка различных условий относительно "типичных" видов наборов предшественников
    // (поддеревьев), генераторы которых собраны ниже в специальных методах
    private boolean checkNessRelation(IElement from, IElement to) {

        boolean result;

        boolean check1 =
                (!(getFullNessTree(from).contains(to)) && getFullNessTree(from).containsAll
                        (getFullNessTree(to))) && getFullNessTree(to).size() > 0;

        // закоментированная часть - "подмножество" более широкого множества ситуаций, проверяемых
        // check1 (т.е., если сработал checkSetsEquality то check1 уж точно сработает, но не
        // наоборот). Но пока нет полной уверенности, что check1 на каком-то
        // множестве "сложноподчиненных"
        // элементов не даст ложноположительных сигналов true. Но, насколько позволяет судить
        // логика реализованной модели, такого быть не может. Код оставлен в
        // таком виде, чтобы в случае
        // проблем с check1, раскомментировать checkSetsEquality и убрать check1, тогда все будет
        // работать нормально. Все это нужно потому, что нет и уверенности, что в варианте без
        // check1 не будет, наоборот, ложноотрицательных возвратов из checkNessRelation.
        result = getPureCloseNess(to).contains(from) || check1 /*|| checkSetsEquality(getPureCloseNess(to),
                getPureCloseNess(from))*/;
        return result;
    }

    // генератор набора, включающего все элементы-предшественники первого поколения, которые есть у
    // элемента-аргумента
    private Set<IElement> getDraftCloseNess(IElement el) {
        Set<IElement> result = new HashSet<>();
        for (IElement e0 : piece.elementList) {
            if (checkNecessity(el, e0)) {
                result.add(e0);
            }
        }
        return result;
    }

    // генератор набора, включающего элементы предшественники всех поколений
    private Set<IElement> getFullNessTree(IElement el) {
        Set<IElement> result = getDraftCloseNess(el);
        Set<IElement> buf = new HashSet<>(result);
        Set<IElement> buf2 = new HashSet<>();
        while (buf.size() > 0) {
            for (IElement elem : buf) {
                result.addAll(getDraftCloseNess(elem));
                buf2.addAll(getDraftCloseNess(elem));
            }
            buf.clear();
            buf.addAll(buf2);
            buf2.clear();
        }
        return result;
    }

    //не используется
    //генератор набора, содержащего элементы-предшественники поколений 2 и выше (все, кроме первого)
    private Set<IElement> getFarNessTree(IElement el) {
        Set<IElement> result = getFullNessTree(el);
        result.removeAll(getPureCloseNess(el));
        return result;
    }

    // генератор набора, включающего элементы-предшественники первого поколения, не являющиеся
    // одновременно предшественниками других элементов предшественников первого поколения
    // элемента el
    private Set<IElement> getPureCloseNess(IElement el) {
        Set<IElement> close = getDraftCloseNess(el);
        Set<IElement> farForAll = new HashSet<>();
        for (IElement elem : close) {
            farForAll.addAll(getFullNessTree(elem));
        }
        for (IElement elem : farForAll) {
            close.remove(elem);
        }
        return close;
    }

    //не нужен в этой версии, но используется в погашенной проверке в checkNessRelation
    private boolean checkSetsEquality(Set<?> set1, Set<?> set2) {
        if (set1 == null || set2 == null) {
            return false;
        }
        if (set1.size() != set2.size()) {
            return false;
        }

        return set1.containsAll(set2);
    }
}
