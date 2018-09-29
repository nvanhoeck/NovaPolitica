package dal.resources;

import bl.domain.countries.Monarch;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Monarchies {
    private String acronym;
    private HashMap<Integer,Monarch> monarchs;

    public Monarchies(String acronym, HashMap<Integer, Monarch> monarchs) {
        this.acronym = acronym;
        this.monarchs = monarchs;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public HashMap<Integer, Monarch> getMonarchs() {
        return monarchs;
    }

    public void setMonarchs(HashMap<Integer, Monarch> monarchs) {
        this.monarchs = monarchs;
    }

    public Monarch updateMonarchs() {
        List<Monarch> sortedMonarchs = monarchs.values().stream().sorted(Comparator.comparingInt(Monarch::getPriority)).collect(Collectors.toList());
        for (int i = 0; i < sortedMonarchs.size()-1; i++) {
            sortedMonarchs.get(i).setHeir(sortedMonarchs.get(i+1));
        }
        return sortedMonarchs.get(0);
    }
}
