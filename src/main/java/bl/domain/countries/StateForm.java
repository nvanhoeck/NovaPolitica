package bl.domain.countries;

import bl.domain.DurationInWeeks;

import java.util.Date;

public class StateForm {
    private final int id;
    private final String name;
    private Centralisation centralisation;
    private Rulership rulership;

    public StateForm() {
        id=0;
        name="";
    }

    public StateForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StateForm(int id, String name, Centralisation centralisation, Rulership rulership) {
        this.id = id;
        this.name = name;
        this.centralisation = centralisation;
        this.rulership = rulership;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Centralisation getCentralisation() {
        return centralisation;
    }

    public void setCentralisation(Centralisation centralisation) {
        this.centralisation = centralisation;
    }

    public Rulership getRulership() {
        return rulership;
    }

    public void setRulership(Rulership rulership) {
        this.rulership = rulership;
    }


}
