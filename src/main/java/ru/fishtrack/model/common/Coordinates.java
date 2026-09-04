package ru.fishtrack.model.common;

import ru.fishtrack.exception.ValidationException;

import java.util.Objects;


public final class Coordinates {
    private final double latitude;
    private final double longitude;

    private Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Coordinates of(double latitude, double longitude) {
        validate(latitude, longitude);
        return new Coordinates(latitude, longitude);
    }

    private static void validate(double latitude, double longitude) {
        if (!Double.isFinite(latitude)) {
            throw new ValidationException("Широта должна быть конечным числом");
        }

        if (!Double.isFinite(longitude)) {
            throw new ValidationException("Долгота должна быть конечным числом");
        }
        if (latitude < -90 || latitude > 90) {
            throw new ValidationException("Широта должна быть в пределах от -90 до 90");
        }
        if (longitude < -180 || longitude > 180) {
            throw new ValidationException("Долгота должна быть в пределах от -180 до 180");
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude,
                                                                              that.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
