package bl.domain.regions;

import bl.domain.parliaments.Parliament;

public class Region {
    private final int id;
    private final String name;
    //TODO private final Image flag;
    //TODO private final Image map;
    private Population population;
    private final String acronym;
    private long GDP;
    private long surface;
    private String capital;
    private Language primaryLanguage;
    private Language secundaryLanguage;
    private Parliament regionalParliament;

    public Region(int id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
    }

    public Region(int id, String name, String acronym, long GDP, long surface, String capital, Language primaryLanguage, Language secundaryLanguage) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.GDP = GDP;
        this.surface = surface;
        this.capital = capital;
        this.primaryLanguage = primaryLanguage;
        this.secundaryLanguage = secundaryLanguage;
    }

    public Region(int id, String name, Population population, String acronym, long GDP, long surface, String capital, Language primaryLanguage, Language secundaryLanguage, Parliament regionalParliament) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.acronym = acronym;
        this.GDP = GDP;
        this.surface = surface;
        this.capital = capital;
        this.primaryLanguage = primaryLanguage;
        this.secundaryLanguage = secundaryLanguage;
        this.regionalParliament = regionalParliament;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public String getAcronym() {
        return acronym;
    }

    public long getGDP() {
        return GDP;
    }

    public void setGDP(long GDP) {
        this.GDP = GDP;
    }

    public long getSurface() {
        return surface;
    }

    public void setSurface(long surface) {
        this.surface = surface;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Language getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(Language primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public Language getSecundaryLanguage() {
        return secundaryLanguage;
    }

    public void setSecundaryLanguage(Language secundaryLanguage) {
        this.secundaryLanguage = secundaryLanguage;
    }

    public Parliament getRegionalParliament() {
        return regionalParliament;
    }

    public void setRegionalParliament(Parliament regionalParliament) {
        this.regionalParliament = regionalParliament;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", acronym='" + acronym + '\'' +
                ", GDP=" + GDP +
                ", surface=" + surface +
                ", capital='" + capital + '\'' +
                ", primaryLanguage=" + primaryLanguage +
                ", secundaryLanguage=" + secundaryLanguage +
                ", regionalParliament=" + regionalParliament +
                '}';
    }
}
