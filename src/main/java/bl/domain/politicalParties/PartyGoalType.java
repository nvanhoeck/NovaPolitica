package bl.domain.politicalParties;

public enum PartyGoalType {
    ELECTIONAL("Elections"), DECCENIAL("10-yearly"), END_GAME("End-Game");
    private final String content;

    PartyGoalType(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
