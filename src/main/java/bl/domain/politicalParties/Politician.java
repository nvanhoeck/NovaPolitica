package bl.domain.politicalParties;

import bl.domain.Gender;
import bl.domain.exceptions.RequestedDataNotFound;
import bl.domain.ideologies.PopulationIdeology;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Politician {
    private final int id;
    private final long salary;
    private final Gender gender;
    //TODO private final Image photo;
    private final String name;
    private double popularity;
    private double trust;
    private double happiness;
    private boolean isMinister;
    private List<PoliticalSkill> skills;
    private HashMap<PopulationIdeology.IdeologyType,PopulationIdeology> populationIdeologies;
    private boolean isNotOnPost;

    public Politician(int id, long salary, Gender gender, String name, double popularity, double trust, double happiness) {
        this.id = id;
        this.salary = salary;
        this.gender = gender;
        this.name = name;
        this.popularity = popularity;
        this.trust = trust;
        this.happiness = happiness;
        this.isMinister = false;
        this.skills = new LinkedList<>();
        this.populationIdeologies = new HashMap<>();
        this.isNotOnPost = false;
    }
    public Politician(int id, long salary, Gender gender, String name,  double popularity, double trust, double happiness, PopulationIdeology primaryIdeology, PopulationIdeology secundaryIdeology) {
        this.id = id;
        this.salary = salary;
        this.gender = gender;
        this.name = name;
        this.popularity = popularity;
        this.trust = trust;
        this.happiness = happiness;
        this.isMinister = false;
        this.skills = new LinkedList<>();
        this.populationIdeologies = new HashMap<>();
        this.isNotOnPost = false;
        populationIdeologies.put(primaryIdeology.getIdeologyType(),primaryIdeology);
        populationIdeologies.put(primaryIdeology.getIdeologyType(),secundaryIdeology);
    }

    public Politician(int id, long salary, Gender gender, String name,  double popularity, double trust, double happiness, PopulationIdeology primaryIdeology, PopulationIdeology secundaryIdeology, PopulationIdeology additionalIdeology) {
        this.id = id;
        this.salary = salary;
        this.gender = gender;
        this.name = name;
        this.popularity = popularity;
        this.trust = trust;
        this.happiness = happiness;
        this.isMinister = false;
        this.skills = new LinkedList<>();
        this.populationIdeologies = new HashMap<>();
        this.isNotOnPost = false;
        populationIdeologies.put(primaryIdeology.getIdeologyType(),primaryIdeology);
        populationIdeologies.put(secundaryIdeology.getIdeologyType(),secundaryIdeology);
        populationIdeologies.put(additionalIdeology.getIdeologyType(),additionalIdeology);
    }


    public int getId() {
        return id;
    }

    public long getSalary() {
        return salary;
    }

    public Gender getGender() {
        return gender;
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

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    public boolean isMinister() {
        return isMinister;
    }

    public void setMinister(boolean minister) {
        isMinister = minister;
    }

    public List<PoliticalSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<PoliticalSkill> skills) {
        this.skills = skills;
    }

    public HashMap<PopulationIdeology.IdeologyType,PopulationIdeology> getPopulationIdeologies() {
        return populationIdeologies;
    }

    public void setPopulationIdeologies(HashMap<PopulationIdeology.IdeologyType,PopulationIdeology> populationIdeologies) {
        this.populationIdeologies = populationIdeologies;
    }

    public boolean isNotOnPost() {
        return isNotOnPost;
    }

    public void setNotOnPost(boolean notOnPost) {
        isNotOnPost = notOnPost;
    }

    public PopulationIdeology getIdeology(PopulationIdeology.IdeologyType type) throws RequestedDataNotFound {
        if (type.equals(PopulationIdeology.IdeologyType.ADDITIONAL) && populationIdeologies.size()==2){
            throw new RequestedDataNotFound("The ideology with type " + type.getName() + " for politician " + this.getName() + " could not be found!");
        }
        return populationIdeologies.get(type);
    }
}
