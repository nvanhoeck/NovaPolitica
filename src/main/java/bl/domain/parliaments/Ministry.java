package bl.domain.parliaments;

import bl.domain.politicalParties.Politician;

public class Ministry {
    private final int id;
    private final String name;
    private Politician minister;
    //TODO private final Image icon;

    public Ministry(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ministry(int id, String name, Politician minister) {
        this.id = id;
        this.name = name;
        this.minister = minister;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Politician getMinister() {
        return minister;
    }

    public void setMinister(Politician minister) {
        this.minister = minister;
    }
}
