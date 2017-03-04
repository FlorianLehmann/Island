package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by florian on 26/02/2017.
 */
public class ExploreTest {
    private State explore;
    private ReadJSON json;
    private Men men;
    private Contracts contracts;
    private Carte map;
    @Before
    public void init(){
        explore = new Explore();
        json=new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        men=new Men(new Point(0,0));
        contracts=new Contracts();
        contracts.add(new PrimaryContract(EnumPrimaryResources.WOOD,5));
        map=new Carte(json);
    }

    @Test
    public void executeTest(){
        assertEquals(explore.execute(men,contracts,map),"{ \"action\": \"explore\" }");
    }

    @Test
    public void waitTest(){
        assertTrue(explore.wait(json) instanceof DefineWay);
    }

    @Test
    public void waitTestWithRessource(){
        explore.execute(men,contracts,map);
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
        assertTrue(explore.wait(json) instanceof Exploit);
    }
}
