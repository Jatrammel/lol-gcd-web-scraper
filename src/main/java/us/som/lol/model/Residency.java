package us.som.lol.model;

public enum Residency {
    RESIDENT("Resident"),
    NONRESIDENT("Non-Resident"),
    NOT_APPLICABLE("Not Applicable");

    private String description;

    Residency(String description) {
        this.description = description;
    }

    public Residency fromString(String str) {
        for (Residency r : Residency.values()) {
            if (r.description.equals(str)) {
                return r;
            }
        }
        return null;
    }
}
