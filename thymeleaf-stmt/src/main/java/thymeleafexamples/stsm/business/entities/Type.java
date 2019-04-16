package thymeleafexamples.stsm.business.entities;

public enum Type {
    PLASTIC("PLASTIC"),
    WOOD("WOOD");

    public static final Type[] ALL = {PLASTIC, WOOD};

    private final String name;

    public static Type forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("null exception");
        }
        if (name.toUpperCase().equals("PLASTIC")) {
            return PLASTIC;
        } else if (name.toUpperCase().equals("WOOD")) {
            return WOOD;
        }
        throw new IllegalArgumentException();
    }

    private Type(final String name) {
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
