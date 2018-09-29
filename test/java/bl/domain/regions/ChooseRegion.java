package bl.domain.regions;

import bl.domain.countries.Country;
import bl.domain.countries.Monarch;
import bl.domain.countries.MonarchType;
import bl.domain.parliaments.FormationInterface;
import bl.domain.parliaments.Government;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dal.jsonMappers.InterfaceSerializer;
import org.junit.Before;
import org.junit.Test;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class ChooseRegion {
    private Country c;
    private HashMap<String,Region> regions;

    @Before
    public void setUp() throws Exception {
        String PATH = System.getProperty("user.dir")+ "/src/main/resources/data/gameData/static/countries/";
        String countryName = "Belgium";
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(FormationInterface.class,new InterfaceSerializer<>(Government.class));
        builder.registerTypeAdapter(MonarchType.class,new InterfaceSerializer<>(Monarch.class));
        Gson gson = builder.create();
        FileReader reader = new FileReader(PATH + countryName+".json");
        c = gson.fromJson(reader,Country.class);
        regions = new HashMap<>();
        regions.putAll(c.getRegions());
    }

    @Test
    public void pickRegion() {
        assertThat(regions.get("FLA").getName(),equalTo("Flanders"));
        assertThat(regions.get("WLN").getName(),equalTo("Wallonia"));
        for (Region r :
                regions.values()) {
            if (r.getId() == 1){
                assertThat(r.getName(),equalTo("Flanders"));
            }else if (r.getId()==2){
                assertThat(r.getName(),equalTo("Wallonia"));
            }
        }
    }


    @Test
    public void checkIfEachAttributeOfRegionIsNotNull() {
        for (Region r :
                regions.values()) {
            Field[] attributes = r.getClass().getDeclaredFields();
            for (Field attribute : attributes) {
                hasProperty(attribute.getName(), notNullValue());
            }
        }

    }



}