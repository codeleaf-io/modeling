package io.codeleaf.modeling.selection.builder.impl;

public interface CombinationBuilder<F, V, C extends CombinationBuilder<F, V, C>> {

    TermBuilder<F, V, C> and();

    TermBuilder<F, V, C> or();

}
