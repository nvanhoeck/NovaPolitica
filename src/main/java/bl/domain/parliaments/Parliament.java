package bl.domain.parliaments;

import bl.domain.politicalParties.PoliticalParty;

import java.util.LinkedList;
import java.util.List;

public class Parliament {
    private final int id;
    private final int totalSeats;
    private final String name;
    private ParliamentaryFormation parliamentFormation;

    public Parliament(int id, int totalSeats, String name) {
        this.id = id;
        this.totalSeats = totalSeats;
        this.name = name;
    }

    public Parliament(int id, int totalSeats, String name, ParliamentaryFormation parliamentFormation) {
        this.id = id;
        this.totalSeats = totalSeats;
        this.name = name;
        this.parliamentFormation = parliamentFormation;
    }


    public int getId() {
        return id;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public String getName() {
        return name;
    }

    public ParliamentaryFormation getParliamentFormation() {
        return parliamentFormation;
    }

    public void setParliamentFormation(ParliamentaryFormation parliamentFormation) {
        this.parliamentFormation = parliamentFormation;
    }
}
