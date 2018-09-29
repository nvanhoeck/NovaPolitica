package bl.domain.politicalParties;

import bl.domain.DurationInWeeks;

public class PartyGoal {
    private final int id;
    private final String name;
    private int levelScore;
    private int totalScore;
    private final int durationInWeeks;
    private final PartyGoalType partyGoalType;
    //TODO private final Image icon;



    public PartyGoal(int id, String name, int levelScore, int totalScore, DurationInWeeks durationInWeeks, PartyGoalType partyGoalType) {
        this.id = id;
        this.name = name;
        this.levelScore = levelScore;
        this.totalScore = totalScore;
        this.durationInWeeks = durationInWeeks.getDurationInWeeks();
        this.partyGoalType = partyGoalType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }


    public PartyGoalType getPartyGoalType() {
        return partyGoalType;
    }
}
