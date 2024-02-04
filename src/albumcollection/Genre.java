package albumcollection;

public enum Genre {
    POP("Pop"),
    COUNTRY("Country"),
    CLASSICAL("Classical"),
    JAZZ("Jazz"),
    UNKNOWN("Unknown");

    final String name;

    Genre (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
