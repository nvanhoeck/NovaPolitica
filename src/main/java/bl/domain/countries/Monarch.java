package bl.domain.countries;

import bl.domain.Gender;

import java.util.Date;

public class Monarch implements MonarchType{
    private final int id;
    private final String name;
    private String title;
    private boolean married;
    private Monarch heir;
    private Gender gender;
    private int priority;
    private Date birthday;

    public Monarch(int id, String name, String title, boolean married, Gender gender, int priority, Date birthday) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.married = married;
        this.gender = gender;
        this.priority = priority;
        this.birthday = birthday;
    }

    public Monarch(int id, String name, String title, boolean married, Monarch heir, Gender gender, int priority, Date birthday) {

        this.id = id;
        this.name = name;
        this.title = title;
        this.married = married;
        this.heir = heir;
        this.gender = gender;
        this.priority = priority;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public Monarch getHeir() {
        return heir;
    }

    public void setHeir(Monarch heir) {
        this.heir = heir;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getBirthday() {
        return birthday;
    }

}
