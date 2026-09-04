package ru.fishtrack.model.water;

import ru.fishtrack.model.WaterBodyType;
import ru.fishtrack.model.common.Coordinates;
import ru.fishtrack.model.common.EntityId;

import java.util.List;

public class WaterBody {
    private final EntityId id;
    private String name;
    private final WaterBodyType type;
    private final String description;
    private final List<FishingSpot> spots;

    private WaterBody(EntityId id, WaterBodyType type, String description, List<FishingSpot> spots, String name) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.spots = spots;
        this.name = name;
    }

    public WaterBody WaterBody(String name, WaterBodyType type, Coordinates coordinates, String description) {
        return new WaterBody(EntityId.random(), type, description, List.of(new FishingSpot(coordinates)), name);
    }
}
