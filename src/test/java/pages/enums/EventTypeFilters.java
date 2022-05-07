package pages.enums;

public enum EventTypeFilters {
    All(0),
    Major(1),
    InternationalLAN(2),
    RegionalLAN(3),
    Online(4),
    LocalLan(5),
    Other(6);

    private Integer idx;
    EventTypeFilters(Integer idx) {
        this.idx = idx;
    }

    public Integer getValue() {
        return idx;
    }
}
