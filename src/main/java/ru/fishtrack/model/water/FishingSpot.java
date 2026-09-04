package ru.fishtrack.model.water;

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
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.depthMeters = depthMeters;
        this.bottomType = bottomType;
        this.hasCurrent = hasCurrent;
        this.description = description;
    }

    public static FishingSpot create(String name, Coordinates coordinates, double depthMeters,
                                     BottomType bottomType, boolean hasCurrent, String description) {
        return new FishingSpot(EntityId.random(), name, coordinates, depthMeters, bottomType, hasCurrent, description);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepthMeters(double depthMeters) {
        this.depthMeters = depthMeters;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return Double.compare(depthMeters,
                              that.depthMeters) == 0 && hasCurrent == that.hasCurrent && Objects.equals(
                id,
                that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates,
                                                                              that.coordinates) && bottomType == that.bottomType && Objects.equals(
                description,
                that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, depthMeters, bottomType, hasCurrent, description);
    }
}
