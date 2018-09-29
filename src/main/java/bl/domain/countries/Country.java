package bl.domain.countries;

import bl.domain.parliaments.Parliament;
import bl.domain.parliaments.ParliamentaryFormation;
import bl.domain.regions.Region;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class Country implements Serializable {
    private final String name;
    //TODO private final Image flag;
    //TODO private final Image map;
    private final String desc;
    private final String acronym;
    private final String denonym;
    private long population;
    private String capital;
    private boolean hasMonarchy;
    private MonarchType monarch;
    private GovernmentForm governmentForm;
    private StateForm stateForm;
    private Parliament parliament;
    private long surface;
    private long GDP;
    private char currency;
    private HashMap<String, Region> regions;

    public Country(String name,String desc, String acronym, String denonym, String capital, boolean hasMonarchy) {
        this.name = name;
        this.desc = desc;
        this.acronym = acronym;
        this.denonym = denonym;
        this.capital = capital;
        this.hasMonarchy = hasMonarchy;
        regions = new HashMap<>();
    }

    public Country(String name, String desc, String acronym, String denonym, long population, String capital, boolean hasMonarchy, MonarchType monarch, GovernmentForm governmentForm, StateForm stateForm, Parliament parliament, long surface, long GDP, char currency, HashMap<String, Region> regions) {
        this.name = name;
        this.desc = desc;
        this.acronym = acronym;
        this.denonym = denonym;
        this.population = population;
        this.capital = capital;
        this.hasMonarchy = hasMonarchy;
        this.monarch = monarch;
        this.governmentForm = governmentForm;
        this.stateForm = stateForm;
        this.parliament = parliament;
        this.surface = surface;
        this.GDP = GDP;
        this.currency = currency;
        this.regions = regions;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getDenonym() {
        return denonym;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public boolean isHasMonarchy() {
        return hasMonarchy;
    }

    public void setHasMonarchy(boolean hasMonarchy) {
        this.hasMonarchy = hasMonarchy;
    }

    public MonarchType getMonarch() {
        return monarch;
    }

    public void setMonarch(MonarchType monarch) {
        this.monarch = monarch;
    }

    public GovernmentForm getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(GovernmentForm governmentForm) {
        this.governmentForm = governmentForm;
    }

    public StateForm getStateForm() {
        return stateForm;
    }

    public void setStateForm(StateForm stateForm) {
        this.stateForm = stateForm;
    }

    public Parliament getParliament() {
        return parliament;
    }

    public void setParliament(Parliament parliament) {
        this.parliament = parliament;
    }

    public long getSurface() {
        return surface;
    }

    public void setSurface(long surface) {
        this.surface = surface;
    }

    public long getGDP() {
        return GDP;
    }

    public void setGDP(long GDP) {
        this.GDP = GDP;
    }

    public char getCurrency() {
        return currency;
    }

    public void setCurrency(char currency) {
        this.currency = currency;
    }

    public HashMap<String, Region> getRegions() {
        return regions;
    }

    public void setRegions(HashMap<String, Region> regions) {
        this.regions = regions;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                ", denonym='" + denonym + '\'' +
                ", population=" + population +
                ", capital='" + capital + '\'' +
                ", hasMonarchy=" + hasMonarchy +
                ", monarch=" + monarch +
                ", governmentForm=" + governmentForm +
                ", stateForm=" + stateForm +
                ", parliament=" + parliament +
                ", surface=" + surface +
                ", GDP=" + GDP +
                ", currency=" + currency +
                ", regions=" + regions +
                '}';
    }
}
