package ru.fishtrack.model.water;

import ru.fishtrack.model.BottomType;
import ru.fishtrack.model.common.Coordinates;
import ru.fishtrack.model.common.EntityId;

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
}
