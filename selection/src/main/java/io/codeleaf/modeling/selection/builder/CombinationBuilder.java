package io.codeleaf.modeling.selection.builder;

public interface CombinationBuilder<F, C extends CombinationBuilder<F, C>> {

    TermBuilder<F, C> and();

    TermBuilder<F, C> or();

}
