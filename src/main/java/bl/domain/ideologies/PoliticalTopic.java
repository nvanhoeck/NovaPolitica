package bl.domain.ideologies;

public class PoliticalTopic {
    private final int id;
    private final String name;
    private PoliticalTopic opposition;



    public PoliticalTopic(int id, String name) {
        this.id = id;
        this.name = name;
        //this.opposition = new PoliticalTopic(0,"none",this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public PoliticalTopic getOpposition() {
        return opposition;
    }

    public void setOpposition(PoliticalTopic opposition) {
        this.opposition = opposition;
    }
}
