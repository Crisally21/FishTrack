package ru.fishtrack.model.common;

import java.util.Objects;
import java.util.UUID;

public class EntityId {
    private final UUID value;

    public EntityId(UUID value) {
        this.value = Objects.requireNonNull(
                value,
                "EntityId value не может быть null"
        );
    }

    public static EntityId random() {
        return new EntityId(UUID.randomUUID());
    }

    public static EntityId of(UUID value) {
        return new EntityId(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityId entityId)) return false;
        return Objects.equals(value, entityId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
