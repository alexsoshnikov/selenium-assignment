package pages.enums;

public enum FiltersTimeRange {
    Featured(0),
    Today(1),
    All(2);

    private Integer idx;
    FiltersTimeRange(Integer idx) {
        this.idx = idx;
    }

    public Integer getValue() {
        return idx;
    }
}
