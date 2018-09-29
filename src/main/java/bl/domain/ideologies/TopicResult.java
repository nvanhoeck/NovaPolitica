package bl.domain.ideologies;

public class TopicResult {
    private final int id;
    private String name;
    private double score;
    private PoliticalTopic politicalTopic;

    public TopicResult(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public TopicResult(int id, String name, double score, PoliticalTopic politicalTopic) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.politicalTopic = politicalTopic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public PoliticalTopic getPoliticalTopic() {
        return politicalTopic;
    }

    public void setPoliticalTopic(PoliticalTopic politicalTopic) {
        this.politicalTopic = politicalTopic;
    }
}
