package pages.enums;

public enum Filters {
    EventType(0),
    PrizePool(1),
    AttendingTeams(2),
    AttendingPlayer(3);

    private Integer idx;
    Filters(Integer idx) {
        this.idx = idx;
    }

    public Integer getValue() {
        return idx;
    }
}
