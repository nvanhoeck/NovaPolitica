package bl.domain;

public enum DurationInWeeks {
    ONE_YEAR(52),TWO_YEARS(104),THREE_YEARS(156),FOUR_YEARS(208),FIVE_YEARS(260),
    TEN_YEARS(520);
    private final int durationInWeeks;

    DurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }
}
