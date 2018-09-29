package bl.domain;

import bl.domain.countries.Country;

public class Game {
    private final int id;
    private final Country country;



    public Game(int id, Country country) {
        this.id = id;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }
}
