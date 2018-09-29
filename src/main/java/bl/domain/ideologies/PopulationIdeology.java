package bl.domain.ideologies;

public class PopulationIdeology {
    private final int id;
    private final String name;
    //TODO private final Image logo;
    private final IdeologyType ideologyType;
    private long amount;
    private PoliticalSpectrum politicalSpectrum;

    public PopulationIdeology(int id, String name, IdeologyType ideologyType, long amount) {
        this.id = id;
        this.name = name;
        this.ideologyType = ideologyType;
        this.amount = amount;
    }


    public PopulationIdeology(int id, String name, IdeologyType ideologyType, long amount, PoliticalSpectrum politicalSpectrum) {
        this.id = id;
        this.name = name;
        this.ideologyType = ideologyType;
        this.amount = amount;
        this.politicalSpectrum = politicalSpectrum;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IdeologyType getIdeologyType() {
        return ideologyType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public PoliticalSpectrum getPoliticalSpectrum() {
        return politicalSpectrum;
    }

    public void setPoliticalSpectrum(PoliticalSpectrum politicalSpectrum) {
        this.politicalSpectrum = politicalSpectrum;
    }

    public enum IdeologyType{
        PRIMARY("Primary"), SECUNDARY("Secundary"), ADDITIONAL("Additional");

        private final String name;

         IdeologyType (String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
