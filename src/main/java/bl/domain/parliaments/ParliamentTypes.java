package bl.domain.parliaments;

public enum ParliamentTypes {
    GOVERNMENT("Government"), OPPOSITION("Opposition"), NON_ELECTED("Non-elected");
    private String type;

    ParliamentTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
