package pl.grizwold.galaga.util;

public class PixelConverter {
    private static final float multiplier = 32f;

    public static float toPixel(float meters) {
        return meters * multiplier;
    }

    public static float toMeters(float pixels) {
        return pixels / multiplier;
    }
}
