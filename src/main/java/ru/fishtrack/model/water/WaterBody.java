package ru.fishtrack.model.water;

import ru.fishtrack.exception.ValidationException;
import ru.fishtrack.model.WaterBodyType;
import ru.fishtrack.model.common.Coordinates;
import ru.fishtrack.model.common.EntityId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WaterBody {

    private final EntityId id;
    private String name;
    private final WaterBodyType type;
    private final Coordinates coordinates;
    private String description;
    private final List<FishingSpot> spots;

    private WaterBody(
            EntityId id,
            String name,
            WaterBodyType type,
            Coordinates coordinates,
            String description
    ) {
        this.id = Objects.requireNonNull(
                id,
                "ID водоёма не может быть null"
        );

        validateName(name);

        this.name = name.trim();

        this.type = Objects.requireNonNull(
                type,
                "Тип водоёма не может быть null"
        );

        this.coordinates = Objects.requireNonNull(
                coordinates,
                "Координаты водоёма не могут быть null"
        );

        this.description = normalizeDescription(description);
        this.spots = new ArrayList<>();
    }

    public static WaterBody create(
            String name,
            WaterBodyType type,
            Coordinates coordinates,
            String description
    ) {
        return new WaterBody(
                EntityId.random(),
                name,
                type,
                coordinates,
                description
        );
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException(
                    "Название водоёма не может быть пустым"
            );
        }
    }

    private static String normalizeDescription(String description) {
        return description == null ? "" : description.trim();
    }

    public void rename(String newName) {
        validateName(newName);
        this.name = newName.trim();
    }

    public void updateDescription(String newDescription) {
        this.description = normalizeDescription(newDescription);
    }

    public void addSpot(FishingSpot spot) {
        Objects.requireNonNull(
                spot,
                "Точка ловли не может быть null"
        );

        if (findSpot(spot.getId()).isPresent()) {
            throw new ValidationException(
                    "Точка с ID " + spot.getId() + " уже добавлена"
            );
        }

        spots.add(spot);
    }

    public Optional<FishingSpot> findSpot(EntityId spotId) {
        Objects.requireNonNull(
                spotId,
                "ID точки не может быть null"
        );

        return spots.stream()
                    .filter(spot -> spot.getId().equals(spotId))
                    .findFirst();
    }

    public void removeSpot(EntityId spotId) {
        Objects.requireNonNull(
                spotId,
                "ID точки не может быть null"
        );

        FishingSpot spot = findSpot(spotId)
                .orElseThrow(() -> new ValidationException(
                        "Точка с ID " + spotId + " не найдена"
                ));

        spots.remove(spot);
    }

    public EntityId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WaterBodyType getType() {
        return type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getDescription() {
        return description;
    }

    public List<FishingSpot> getSpots() {
        return List.copyOf(spots);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof WaterBody other)) {
            return false;
        }

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "WaterBody{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", coordinates=" + coordinates +
                ", description='" + description + '\'' +
                ", spotsCount=" + spots.size() +
                '}';
    }
}