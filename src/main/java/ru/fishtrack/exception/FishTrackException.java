package ru.fishtrack.exception;

public class FishTrackException extends RuntimeException {
    public FishTrackException(String message, Throwable cause) {
        super(message, cause);
    }
    public FishTrackException(String message) {
        super(message);
    }
}
