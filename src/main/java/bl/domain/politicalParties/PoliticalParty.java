package bl.domain.politicalParties;

import bl.domain.exceptions.RequestedDataNotFound;
import bl.domain.ideologies.PoliticalSpectrum;
import bl.domain.ideologies.PopulationIdeology;

import java.util.HashMap;
import java.util.LinkedList;

public class PoliticalParty {
    private final int id;
    private final String name;
    private final String shortname;
    private final String primaryColor;
    private final String secundaryColor;
    //Image private final Image partyLogo;
    private double budget;
    private double popularity;
    private double trust;
    private HashMap<Integer,Politician> partyMembers;
    private LinkedList<PartyGoal> partyGoals;
    private HashMap<PopulationIdeology.IdeologyType,PopulationIdeology> ideologies;
    private PoliticalSpectrum politicalSpectrum;
    private int amountOfFederalSeats;
    private int amountOfRegionalSeats;

    public PoliticalParty(int id, String name, String shortname, String primaryColor, String secundaryColor) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.primaryColor = primaryColor;
        this.secundaryColor = secundaryColor;
        budget=0;
        popularity=0;
        trust = 0;
        partyMembers = new HashMap<>();
        partyGoals = new LinkedList<>();
        ideologies = new HashMap<>();
        amountOfFederalSeats=0;
        amountOfRegionalSeats=0;
    }

    public PoliticalParty(int id, String name, String shortname, String primaryColor, String secundaryColor, double budget, double popularity, double trust, HashMap<Integer, Politician> partyMembers, LinkedList<PartyGoal> partyGoals, HashMap<PopulationIdeology.IdeologyType, PopulationIdeology> ideologies, PoliticalSpectrum politicalSpectrum, int amountOfFederalSeats, int amountOfRegionalSeats) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.primaryColor = primaryColor;
        this.secundaryColor = secundaryColor;
        this.budget = budget;
        this.popularity = popularity;
        this.trust = trust;
        this.partyMembers = partyMembers;
        this.partyGoals = partyGoals;
        this.ideologies = ideologies;
        this.politicalSpectrum = politicalSpectrum;
        this.amountOfFederalSeats = amountOfFederalSeats;
        this.amountOfRegionalSeats = amountOfRegionalSeats;
    }

    public PoliticalParty(int id, String name, String shortname, String primary, String secundary, long budget, double popularity, double trust, int federalseats, int regionalSets) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.primaryColor = primary;
        this.secundaryColor = secundary;
        this.budget = budget;
        this.popularity = popularity;
        this.trust = trust;
        this.amountOfRegionalSeats = regionalSets;
        this.amountOfFederalSeats = federalseats;
        this.partyMembers = new HashMap<>();
        this.partyGoals = new LinkedList<>();
        this.ideologies = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortname() {
        return shortname;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecundaryColor() {
        return secundaryColor;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
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

    public HashMap<Integer, Politician> getPartyMembers() {
        return partyMembers;
    }

    public void setPartyMembers(HashMap<Integer, Politician> partyMembers) {
        this.partyMembers = partyMembers;
    }

    public LinkedList<PartyGoal> getPartyGoals() {
        return partyGoals;
    }

    public void setPartyGoals(LinkedList<PartyGoal> partyGoals) {
        this.partyGoals = partyGoals;
    }

    public HashMap<PopulationIdeology.IdeologyType, PopulationIdeology> getIdeologies() {
        return ideologies;
    }

    public void setIdeologies(HashMap<PopulationIdeology.IdeologyType, PopulationIdeology> ideologies) {
        this.ideologies = ideologies;
    }

    public PoliticalSpectrum getPoliticalSpectrum() {
        return politicalSpectrum;
    }

    public void setPoliticalSpectrum(PoliticalSpectrum politicalSpectrum) {
        this.politicalSpectrum = politicalSpectrum;
    }

    public int getAmountOfFederalSeats() {
        return amountOfFederalSeats;
    }

    public void setAmountOfFederalSeats(int amountOfFederalSeats) {
        this.amountOfFederalSeats = amountOfFederalSeats;
    }

    public int getAmountOfRegionalSeats() {
        return amountOfRegionalSeats;
    }

    public void setAmountOfRegionalSeats(int amountOfRegionalSeats) {
        this.amountOfRegionalSeats = amountOfRegionalSeats;
    }

    private PopulationIdeology getPartyIdeology(PopulationIdeology.IdeologyType type) throws RequestedDataNotFound {
        if (type.equals(PopulationIdeology.IdeologyType.ADDITIONAL) && ideologies.size()==2) {
            throw new RequestedDataNotFound("The ideology with type " + type.getName() + " for political party " + this.getName()+ " could not be found!");
        } else {
            return ideologies.get(type);
        }
    }

    private void addPartyGoal(PartyGoal partyGoal) {
        this.partyGoals.add(partyGoal);
    }

    private void addPartyMember(Politician partyMember) {
        this.partyMembers.put(partyMember.getId(),partyMember);
    }

    private void removePartyMember(Politician politician) {
        this.partyMembers.remove(politician.getId());
    }

    private Politician getPartyMember(int id) {
        return this.partyMembers.get(id);
    }
}
