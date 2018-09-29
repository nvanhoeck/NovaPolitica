package bl.domain.regions;

import java.util.HashMap;

public class Religion {
    private final int id;
    private final String name;
    //TODO private final Image logo;
    private long religionAmountByPopulation;


    public Religion(int id, String name) {
        this.id = id;
        this.name = name;
        this.religionAmountByPopulation = 0;
    }

    public Religion(int id, String name, long religionAmountByPopulation) {
        this.id = id;
        this.name = name;
        this.religionAmountByPopulation = religionAmountByPopulation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getReligionAmountByPopulation() {
        return religionAmountByPopulation;
    }

    public void setReligionAmountByPopulation(long religionAmountByPopulation) {
        this.religionAmountByPopulation = religionAmountByPopulation;
    }
}
