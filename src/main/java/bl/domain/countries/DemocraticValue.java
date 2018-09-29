package bl.domain.countries;

public class DemocraticValue {
    private final int id;
    private double score;
    private final int MAXSCORE=10;
    private final int MINSCORE=-10;

    public DemocraticValue(int id, double score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getMAXSCORE() {
        return MAXSCORE;
    }

    public int getMINSCORE() {
        return MINSCORE;
    }


}
