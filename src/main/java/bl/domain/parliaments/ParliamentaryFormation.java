package bl.domain.parliaments;

import bl.domain.politicalParties.PoliticalParty;

import java.util.HashMap;
import java.util.LinkedList;

public class ParliamentaryFormation implements FormationInterface{
    private final int id;
    private final String name;
    private final int amountOfSeats;
    private HashMap<ParliamentTypes, FormationInterface> governmentAndOpposition;
    private HashMap<String, PoliticalParty> nonElectedParties;


    public ParliamentaryFormation(int id, String name, int amountOfSeats) {
        this.id = id;
        this.name = name;
        this.amountOfSeats = amountOfSeats;
        this.governmentAndOpposition = new HashMap<>();
        nonElectedParties = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public HashMap<ParliamentTypes, FormationInterface> getGovernmentAndOpposition() {
        return governmentAndOpposition;
    }

    public void setGovernmentAndOpposition(HashMap<ParliamentTypes, FormationInterface> governmentAndOpposition) {
        this.governmentAndOpposition = governmentAndOpposition;
    }

    public HashMap<String, PoliticalParty> getNonElectedParties() {
        return nonElectedParties;
    }

    public void setNonElectedParties(HashMap<String, PoliticalParty> nonElectedParties) {
        this.nonElectedParties = nonElectedParties;
    }

    public void transferToNonElectedParties(String acronym) {
        this.nonElectedParties.put(this.getElectedPaties().get(acronym).getShortname(),this.getElectedPaties().get(acronym));
        Government g = (Government) this.governmentAndOpposition.get(ParliamentTypes.GOVERNMENT);
        g.getParties().remove(acronym);
    }

    @Override
    public HashMap<String, PoliticalParty> getParties() {
        HashMap<String,PoliticalParty> parties= new HashMap<>();
        parties.putAll(governmentAndOpposition.get(ParliamentTypes.GOVERNMENT).getParties());
        parties.putAll(governmentAndOpposition.get(ParliamentTypes.OPPOSITION).getParties());
        parties.putAll(nonElectedParties);
        return parties;
    }

    @Override
    public HashMap<String, PoliticalParty> getElectedPaties() {
        HashMap<String,PoliticalParty> parties= new HashMap<>();
        parties.putAll(governmentAndOpposition.get(ParliamentTypes.GOVERNMENT).getParties());
        parties.putAll(governmentAndOpposition.get(ParliamentTypes.OPPOSITION).getParties());
        return parties;
    }
}


