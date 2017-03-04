package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.SecondaryContract;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 26/02/2017.
 */
public class ExploreSecondTest {

    private ReadJSON json;
    private Men men;
    private Contracts contracts;
    private Carte map;
    @Before
    public void init(){
        json=new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        men=new Men(new Point(0,0));
        contracts=new Contracts();
        contracts.add(new SecondaryContract(EnumManufacturedResources.PLANK,5));
        map=new Carte(json);
    }

    @Test
    public void executeTest(){
        State state=new ExploreSecond(0);
        assertEquals(state.execute(men,contracts,map),"{ \"action\": \"explore\" }");
    }

    @Test
    public void waitTest(){
        State state=new ExploreSecond(0);
        assertTrue(state.wait(json) instanceof DefineWaySecond);
    }

    @Test
    public void waitTestWithRessource(){
        State state=new ExploreSecond(0);
        state.execute(men,contracts,map);
        json.read("{\n" +
                "  \"cost\": 5,\n" +
                "  \"extras\": {\n" +
                "    \"resources\": [\n" +
                "      { \"amount\": \"HIGH\", \"resource\": \"FUR\", \"cond\": \"FAIR\" },\n" +
                "      { \"amount\": \"LOW\", \"resource\": \"WOOD\", \"cond\": \"HARSH\" }\n" +
                "    ],\n" +
                "    \"pois\": [{\"kind\": \"Creek\", \"id\": \"43e3eb42-50f0-47c5-afa3-16cd3d50faff\"}]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}");
        assertTrue(state.wait(json) instanceof ExploitSecond);
    }
}
