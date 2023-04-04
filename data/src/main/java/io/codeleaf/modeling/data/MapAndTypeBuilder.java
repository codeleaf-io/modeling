package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class MapAndTypeBuilder<T> {

    private final Function<? super MapWithType, T> valueWithTypeFunction;

    private MapType<?> mapType;
    private MapWithType.Builder builder;

    MapAndTypeBuilder(Function<? super MapWithType, T> valueWithTypeFunction) {
        this.valueWithTypeFunction = valueWithTypeFunction;
    }

    public static MapAndTypeBuilder<MapWithType> create() {
        return new MapAndTypeBuilder<>(mapWithType -> mapWithType);
    }

    public ValueAndTypeBuilder<MapAndTypeBuilder<T>> key(String key) {
        Objects.requireNonNull(key);
        return new ValueAndTypeBuilder<>(valueWithType -> {
            if (builder == null) {
                mapType = MapType.create(valueWithType.getType());
                builder = new MapWithType.Builder(mapType);
            }
            if (!valueWithType.getType().equals(mapType.getEntryValueType())) {
                throw new IllegalArgumentException("Invalid type!");
            }
            builder.withEntry(key, valueWithType);
            return this;
        });
    }

    public T endMap() {
        return valueWithTypeFunction.apply(builder.build());
    }

}
