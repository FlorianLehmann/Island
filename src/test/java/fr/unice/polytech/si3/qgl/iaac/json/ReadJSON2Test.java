package fr.unice.polytech.si3.qgl.iaac.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.WEST;
import static org.junit.Assert.assertEquals;

/**
 * Created by florian on 22/03/2017.
 */
public class ReadJSON2Test {

    private ReadJSON2 readJSON2;

    @Before
    public void defineContext() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        readJSON2 = mapper.readValue("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}", ReadJSON2.class);
    }

    @Test
    public void shouldHave12Men() {
        assertEquals(12, readJSON2.getMen());
    }

    @Test
    public void shouldHaveABudgetOf10000() {
        assertEquals(10000, readJSON2.getBudget());
    }

    @Test
    public void shouldHeadingW() {
        assertEquals(WEST, readJSON2.getHeading());
    }

}
