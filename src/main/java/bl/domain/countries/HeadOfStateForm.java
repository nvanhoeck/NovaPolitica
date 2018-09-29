package bl.domain.countries;

public class HeadOfStateForm {
    private final int id;
    private final String name;

    public HeadOfStateForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
