package bl.domain.parliaments;

import bl.domain.politicalParties.PoliticalParty;

import java.util.HashMap;

public interface FormationInterface {
    HashMap<String, PoliticalParty> getParties();
    HashMap<String,PoliticalParty> getElectedPaties();
}
