package io.codeleaf.modeling.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public interface CombinationSelection extends Selection {

    List<Selection> getSelections();

    abstract class AbstractBuilder<S extends CombinationSelection> {

        private final List<Selection> selections = new ArrayList<>();

        @SuppressWarnings("unchecked")
        public AbstractBuilder<S> withSelection(Selection selection) {
            Objects.requireNonNull(selection);
            selections.add(selection);
            return this;
        }

        protected void validate() {
            if (selections.isEmpty()) {
                throw new IllegalStateException("Needs at least 1 selection!");
            }
            for (Selection selection : selections) {
                if (selection == null) {
                    throw new IllegalStateException("No null selections allowed!");
                }
            }
        }

        public S build() {
            validate();
            return build(Collections.unmodifiableList(new ArrayList<>(selections)));
        }

        protected abstract S build(List<Selection> selections);

    }

}
