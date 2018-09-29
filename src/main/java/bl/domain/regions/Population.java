package bl.domain.regions;

import bl.domain.ideologies.PopulationIdeology;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Population {
    private final int id;
    private final String name;
    private long amount;
    private HashMap<Integer,PopulationIdeology> populationIdeologies;
    private HashMap<Integer,Religion> religions;

    public Population(int id, String name) {
        this.id = id;
        this.name = name;
        this.populationIdeologies = new HashMap<>();
        this.religions = new HashMap<>();
    }

    public Population(int id, String name, long amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.populationIdeologies = new HashMap<>();
        this.religions = new HashMap<>();
    }

    public void updateAmountOfReligion(int id, long updateAmount) {
        this.religions.get(id).setReligionAmountByPopulation(this.religions.get(id).getReligionAmountByPopulation()+updateAmount);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public HashMap<Integer, PopulationIdeology> getPopulationIdeologies() {
        return populationIdeologies;
    }

    public void setPopulationIdeologies(HashMap<Integer, PopulationIdeology> populationIdeologies) {
        this.populationIdeologies = populationIdeologies;
    }

    public HashMap<Integer, Religion> getReligions() {
        return religions;
    }

    public void setReligions(HashMap<Integer, Religion> religions) {
        this.religions = religions;
    }

}
