package ru.fishtrack.model.water;

import ru.fishtrack.exception.ValidationException;
import ru.fishtrack.model.BottomType;
import ru.fishtrack.model.common.Coordinates;
import ru.fishtrack.model.common.EntityId;

import java.util.Objects;

public class FishingSpot {
    private final EntityId id;
    private String name;
    private final Coordinates coordinates;
    private double depthMeters;
    private BottomType bottomType;
    private boolean hasCurrent;
    private String description;

    private FishingSpot(EntityId id,
                        String name,
                        Coordinates coordinates,
                        double depthMeters,
                        BottomType bottomType,
                        boolean hasCurrent,
                        String description) {
        this.id = Objects.requireNonNull(id, "ID точки не может быть null");
        validateName(name);
        validateDepth(depthMeters);
        this.name = name.trim();
        this.coordinates = Objects.requireNonNull(coordinates, "Координаты точки не могут быть null");
        this.depthMeters = depthMeters;
        this.bottomType = Objects.requireNonNull(bottomType, "Тип дна не может быть null");
        this.hasCurrent = hasCurrent;
        this.description = description == null ? "" : description.trim();
    }

    public static FishingSpot create(String name, Coordinates coordinates, double depthMeters,
                                     BottomType bottomType, boolean hasCurrent, String description) {
        return new FishingSpot(EntityId.random(), name, coordinates, depthMeters, bottomType, hasCurrent, description);
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Название точки не может быть пустым");
        }
    }

    private static void validateDepth(double depthMeters) {
        if (!Double.isFinite(depthMeters) || depthMeters < 0) {
            throw new ValidationException("Глубина должна быть конечным неотрицательным числом");
        }
    }

    public void rename(String newName) {
        validateName(newName);
        this.name = newName.trim();
    }

    public void updateDepth(double newDepth) {
        validateDepth(newDepth);
        this.depthMeters = newDepth;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription == null ? "" : newDescription.trim();
    }

    public EntityId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getDepthMeters() {
        return depthMeters;
    }

    public BottomType getBottomType() {
        return bottomType;
    }

    public boolean isHasCurrent() {
        return hasCurrent;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FishingSpot that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
