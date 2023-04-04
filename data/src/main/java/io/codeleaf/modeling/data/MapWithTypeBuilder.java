package io.codeleaf.modeling.data;

import java.util.Objects;
import java.util.function.Function;

public final class MapWithTypeBuilder<T> {

    private final MapType<?> mapType;
    private final Function<? super MapWithType, T> valueWithTypeFunction;
    private final MapWithType.Builder builder;

    MapWithTypeBuilder(MapType<?> mapType, Function<? super MapWithType, T> valueWithTypeFunction) {
        this.mapType = mapType;
        this.valueWithTypeFunction = valueWithTypeFunction;
        builder = new MapWithType.Builder(mapType);
    }

    public static MapWithTypeBuilder<MapWithType> create(MapType<?> mapType) {
        Objects.requireNonNull(mapType);
        return new MapWithTypeBuilder<>(mapType, mapWithType -> mapWithType);
    }

    public ValueWithTypeBuilder<MapWithTypeBuilder<T>> key(String key) {
        Objects.requireNonNull(key);
        return new ValueWithTypeBuilder<>(mapType.getEntryValueType(), valueWithType -> {
            builder.withEntry(key, valueWithType);
            return this;
        });
    }

    public T endMap() {
        return valueWithTypeFunction.apply(builder.build());
    }
}
