package bl.domain.parliaments;

import bl.domain.politicalParties.PoliticalParty;

import java.util.HashMap;

public class Government implements FormationInterface{
    private final int id;
    private final String name;
    private double popularity;
    private double trust;
    private final int MAXSCORE =100;
    private final int MINSCORE = 0;
    private final int amountOfSeats;
    private HashMap<String, PoliticalParty> parties;
    private HashMap<Integer,Ministry> ministries;

    public Government(int id, String name, double popularity, double trust, int amountOfSeats) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.trust = trust;
        this.amountOfSeats = amountOfSeats;
        this.parties = new HashMap<>();
        this.ministries = new HashMap<>();
    }

    public Government(int id, String name, double popularity, double trust, int amountOfSeats, HashMap<String, PoliticalParty> parties, HashMap<Integer, Ministry> ministries) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.trust = trust;
        this.amountOfSeats = amountOfSeats;
        this.parties = parties;
        this.ministries = ministries;
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

    @Override
    public HashMap<String, PoliticalParty> getElectedPaties() {
        return parties;
    }

    public void setParties(HashMap<String, PoliticalParty> parties) {
        this.parties = parties;
    }

    public HashMap<Integer, Ministry> getMinistries() {
        return ministries;
    }

    public void setMinistries(HashMap<Integer, Ministry> ministries) {
        this.ministries = ministries;
    }
}
