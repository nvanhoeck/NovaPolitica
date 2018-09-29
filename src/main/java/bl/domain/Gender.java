package bl.domain;

public enum Gender {
    MALE("Male", "M"), FEMALE("Female","F");
    private final String fullName;
    private final String shortName;

    Gender(String fullName, String shortName) {
        this.fullName=fullName;
        this.shortName=shortName;
    }

    public String getFullName() {
        return fullName;
    }


    public String getShortName() {
        return shortName;
    }

}
