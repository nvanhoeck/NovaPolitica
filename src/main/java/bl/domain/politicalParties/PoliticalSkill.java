package bl.domain.politicalParties;

public class PoliticalSkill {
    private final int id;
    private final String name;
    private final String desc;
    private double score;
    //TODO private final Image icon;

    public PoliticalSkill(int id, String name, String desc, double score) {
        this.id = id;
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }
}
