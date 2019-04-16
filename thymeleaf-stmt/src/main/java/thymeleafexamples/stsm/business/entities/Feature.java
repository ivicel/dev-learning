package thymeleafexamples.stsm.business.entities;

public enum Feature {
    SEEDSTARTER_SPECIFIC_SUBSTARTE("SEEDSTARTER_SPECIFIC_SUBSTARTE"),
    FERTILIZER("FERTILIZER"),
    PH_CORRECTOR("PH_CORRECTOR");

    public static final Feature[] ALL = {
            SEEDSTARTER_SPECIFIC_SUBSTARTE,
            FERTILIZER,
            PH_CORRECTOR
    };
    private final String name;

    public static Feature forName(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        if (name.toUpperCase().equals("SEEDSTARTER_SPECIFIC_SUBSTARTE")) {
            return SEEDSTARTER_SPECIFIC_SUBSTARTE;
        } else if (name.toUpperCase().equals("FERTILIZER")) {
            return FERTILIZER;
        } else if (name.toUpperCase().equals("PH_CORRECTOR")) {
            return PH_CORRECTOR;
        }
        throw new IllegalArgumentException();
    }

    Feature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
