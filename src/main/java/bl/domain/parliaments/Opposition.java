package bl.domain.parliaments;

import bl.domain.politicalParties.PoliticalParty;

import java.util.HashMap;

public class Opposition implements FormationInterface {
    private final int id;
    private final String name;
    private double popularity;
    private double trust;
    private final int MAXSCORE =100;
    private final int MINSCORE = 0;
    private final int amountOfSeats;
    private HashMap<String,PoliticalParty> parties;

    public Opposition(int id, String name, double popularity, double trust, int amountOfSeats) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.trust = trust;
        this.amountOfSeats = amountOfSeats;
        this.parties = new HashMap<>();
    }

    public Opposition(int id, String name, double popularity, double trust, int amountOfSeats, HashMap<String, PoliticalParty> parties) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.trust = trust;
        this.amountOfSeats = amountOfSeats;
        this.parties = parties;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getTrust() {
        return trust;
    }

    public void setTrust(double trust) {
        this.trust = trust;
    }

    public int getMAXSCORE() {
        return MAXSCORE;
    }

    public int getMINSCORE() {
        return MINSCORE;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    @Override
    public HashMap<String, PoliticalParty> getParties() {
        return parties;
    }

    public void setParties(HashMap<String, PoliticalParty> parties) {
        this.parties = parties;
    }

    @Override
    public HashMap<String, PoliticalParty> getElectedPaties() {
        return parties;
    }
}
