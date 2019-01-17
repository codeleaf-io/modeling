package io.codeleaf.modeling.selection;

public final class InRangeSelection<T> implements Selection {

    private final T beginIncl;
    private final T endExc;

    public InRangeSelection(T beginIncl, T endExc) {
        this.beginIncl = beginIncl;
        this.endExc = endExc;
    }

    public T getBeginIncl() {
        return beginIncl;
    }

    public T getEndExc() {
        return endExc;
    }

}
