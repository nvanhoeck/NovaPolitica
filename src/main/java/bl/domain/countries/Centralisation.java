package bl.domain.countries;

public class Centralisation {
    private final int id;
    private final String name;
    private double score;
    private final int MAXSCORE=10;
    private final int MINSCORE=-10;

    public Centralisation(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
