package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.exceptions.NoBudgetfield;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoHeadingField;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by lehmann on 11/02/17.
 */
public class ReadJSONTest {

    private ReadJSON json;


    @Before
    public void defineContext() {
        json = new ReadJSON("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
    }



    @Test
    public void initDroneWithoutFieldForHeading() {
        try {
            json.initDrone();
            assertTrue(true);
        } catch (NoHeadingField exception) {
            assertTrue(false);
        }
    }

    @Test
    public void initBudgetWithoutFieldForBudget() {
        try {
            json.initBudget();
            assertTrue(true);
        } catch (NoBudgetfield exception) {
            assertTrue(false);
        }
    }

    @Test
    public void initDrone() {
        try {
            new ReadJSON("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, " +
                    "\"resource\": \"WOOD\" }, { \"amount\": 200, \"resource\": \"GLASS\" }]}").initDrone();
            assertTrue(false);
        } catch (NoHeadingField exception) {
            assertTrue(true);
        }
    }

    @Test
    public void initBudget() {
        try {
            new ReadJSON("{ \"men\": 12, \"contracts\": [ { \"amount\": 600, " +
                    "\"resource\": \"WOOD\" }, { \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}").initBudget();
            assertTrue(false);
        } catch (NoBudgetfield exception) {
            assertTrue(true);
        }
    }


}