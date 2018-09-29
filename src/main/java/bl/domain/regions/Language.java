package bl.domain.regions;

public class Language {
    private final int id;
    private final String name;
    private final String adjectif;

    public Language(int id, String name, String adjectif) {
        this.id = id;
        this.name = name;
        this.adjectif = adjectif;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAdjectif() {
        return adjectif;
    }

}
