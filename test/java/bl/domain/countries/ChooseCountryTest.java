package bl.domain.countries;
import bl.domain.parliaments.FormationInterface;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.regions.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dal.jsonMappers.InterfaceSerializer;
import org.junit.Before;
import org.junit.Test;
import java.io.FileReader;
import java.lang.reflect.Field;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ChooseCountryTest {
    private Country c;
    private Region r;

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
    }

    @Test
    public void pickCountry() {
        assertThat(c.getName(),equalTo("Belgium"));
    }

    @Test
    public void pickRegion() {
        assertThat(c.getRegions().size(),equalTo(2));
        assertThat(c.getRegions().get("FLA").getName(),equalTo("Flanders"));
    }

    @Test
    public void getAllParties(){
        assertThat(c.getParliament().getParliamentFormation().getParties().size(),equalTo(13));
        assertThat(c.getRegions().get("FLA").getRegionalParliament().getParliamentFormation().getParties().size(),equalTo(9));
        assertThat(c.getRegions().get("WLN").getRegionalParliament().getParliamentFormation().getParties().size(),equalTo(8));

    }

    @Test
    public void checkIfEachParliamentPartyHasAPartyMember(){
        c.getParliament().getParliamentFormation().getElectedPaties().values().forEach(politicalParty ->
                assertThat(politicalParty.getPartyMembers().size(),greaterThan(0))
        );
        c.getRegions().get("FLA")
                .getRegionalParliament().getParliamentFormation().getElectedPaties().values().forEach(politicalParty ->
                assertThat(politicalParty.getPartyMembers().size(),greaterThan(0))
        );
        c.getRegions().get("WLN")
                .getRegionalParliament().getParliamentFormation().getElectedPaties().values().forEach(politicalParty ->
                assertThat(politicalParty.getPartyMembers().size(),greaterThan(0))
        );

    }

    @Test
    public void checkIfCountryHasMonarch() {
        if(c.isHasMonarchy()) {
            assertThat(c.getMonarch().getClass(), equalTo(Monarch.class));
        }else {
            assertThat(c.getMonarch().getClass(),equalTo(NoMonarch.class));
        }
    }

    @Test
    public void checkIfEachAttributeOfCountryIsNotNull() {
        Field[] attributes = c.getClass().getDeclaredFields();
        for (Field attribute : attributes) {
            hasProperty(attribute.getName(), notNullValue());
        }
    }

    @Test
    public void checkIfCountryHasMinistries() {
        Government g = (Government) c.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
        assertThat(g.getMinistries().size(),greaterThan(0));
    }

    @Test
    public void checkIfRegionsHasMinistries() {
        for (Region r :
                c.getRegions().values()) {
            Government g = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
            assertThat(g.getMinistries().size(), greaterThan(0));
        }
    }



    /*
    @Test
    public void checkIfPartyFederalEqualsPartyRegional(){
        //TODO Zorg ervoor dat elke partij gelijk is (Overkoepelende partij)
        for (PoliticalParty pc :
                c.getParliament().getParliamentFormation().getParties().values()) {
            for (Region r :
                    c.getRegions().values()) {
                if (r.getRegionalParliament().getParliamentFormation().getParties().containsKey(pc.getShortname())){
                    PoliticalParty pr = r.getRegionalParliament().getParliamentFormation().getParties().get(pc.getShortname());
                    assertThat(pr,equalTo(pc));
                }
            }
            }
        }
    */
}