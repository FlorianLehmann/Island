package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.Contract;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 26/02/2017.
 */
public class TransformSecondTest {
    private ReadJSON json;
    private Men men;
    private Contracts contracts;
    private Carte map;

    @Before
    public void init() {
        json = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        men = new Men(new Point(0, 0));
        contracts = new Contracts();
        contracts.add(new Contract(EnumManufacturedResources.PLANK, 5));
        map = new Carte(json);
    }

    @Test
    public void executeTest() {
        State state = new TransformSecond(10);
        assertEquals(state.execute(men, contracts, map), "{ \"action\": \"transform\", \"parameters\": { \"WOOD\": 10 }}");
    }

    @Test
    public void waitTestWithContractNotCompleted() {
        State state = new TransformSecond(10);
        state.execute(men, contracts, map);
        json.read("{ \"cost\": 5, \"extras\": { \"production\": 5, \"kind\": \"PLANK\" },\"status\": \"OK\" }");
        assertTrue(state.wait(json) instanceof DefineWaySecond);
    }
}