package bl.domain.countries;

public final class NoMonarch implements MonarchType{
    private final int id = 0;
    private final String name = "No Monarch";
    private final String title = "None";
    private final boolean married = false;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public boolean isMarried() {
        return married;
    }


}
