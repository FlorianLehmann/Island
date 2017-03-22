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
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome.GLACIER;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.GLASS;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.FUR;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.WOOD;
import static org.junit.Assert.assertEquals;

/**
 * Created by florian on 22/03/2017.
 */
public class ReadJSON2Test {

    private ReadJSON2 readJSON2;
    private Answer answer;

    @Before
    public void defineContext() throws IOException {
        readJSON2 = new ReadJSON2();
        answer = readJSON2.read("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
    }

    @Test
    public void shouldHave12Men() {
        assertEquals(12, answer.getMen());
    }

    @Test
    public void shouldHaveABudgetOf10000() {
        assertEquals(10000, answer.getBudget());
    }

    @Test
    public void shouldHeadingW() {
        assertEquals(WEST, answer.getHeading());
    }

    @Test
    public void shouldContainTwoContracts() {
        assertEquals(2, answer.getContracts().size());
        assertEquals(600, answer.getContracts().get(0).getRequired());
        assertEquals(WOOD, answer.getContracts().get(0).getName());
        assertEquals(200, answer.getContracts().get(1).getRequired());
        assertEquals(GLASS, answer.getContracts().get(1).getName());
    }

    @Test
    public void shouldReadFlyJSON() throws IOException {
        answer = readJSON2.read("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");
        assertEquals(2, answer.getCost());
        assertEquals("OK", answer.getStatus());
    }

    @Test
    public void shouldReadHeadingJSON() throws IOException {
        answer = readJSON2.read("{ \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }");
        assertEquals(4, answer.getCost());
        assertEquals("OK", answer.getStatus());
    }

    @Test
    public void shouldReadEchoJSON() throws IOException {
        answer = readJSON2.read("{ \"cost\": 1, \"extras\": { \"range\": 1, \"found\": \"OUT_OF_RANGE\" }, " +
                "\"status\": \"OK\" }");
        assertEquals(1, answer.getCost());
        assertEquals(1, answer.getRange());
        assertEquals("OUT_OF_RANGE", answer.getFound());
        assertEquals("OK", answer.getStatus());
    }

    @Test
    public void shouldReadScanJSON() throws IOException {
        answer = readJSON2.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": [\"creeks\"], \"sites\": [\"sites\"]}, \"status\": \"OK\"}");
        assertEquals(2, answer.getCost());
        assertEquals(GLACIER, answer.getBiomes().get(0));
        assertEquals("creeks", answer.getCreeks().get(0));
        assertEquals("sites", answer.getSites().get(0));
        assertEquals("OK", answer.getStatus());
    }

    @Test
    public void shouldReadExploreJSON() throws IOException {
        answer = readJSON2.read("{\"cost\": 5,\"extras\": {\"resources\": [ { \"amount\": \"HIGH\", " +
                "\"resource\": \"FUR\", \"cond\": \"FAIR\" }, { \"amount\": \"LOW\", " +
                "\"resource\": \"WOOD\", \"cond\": \"HARSH\" }]," +
                "\"pois\": [{\"kind\": \"Creek\", \"id\": \"43e3eb42-50f0-47c5-afa3-16cd3d50faff\"}]}," +
                "\"status\": \"OK\"}");
        assertEquals(5, answer.getCost());
        assertEquals(FUR, answer.getResources().get(0));
        assertEquals("OK", answer.getStatus());
    }

    @Test
    public void shouldReadExploitJSON() throws IOException {
        answer = readJSON2.read("{ \"cost\": 3, \"extras\": {\"amount\": 9}, \"status\": \"OK\" }");
        assertEquals(3, answer.getCost());
        assertEquals(9, answer.getAmount());
        assertEquals("OK", answer.getStatus());
    }

    @Test
    public void shouldReadTransformJSON() throws IOException {
        answer = readJSON2.read("{ \"cost\": 5, \"extras\": { \"production\": 1, \"kind\": \"GLASS\" },\"status\": \"OK\" }");
        assertEquals(5, answer.getCost());
        assertEquals(1, answer.getProduction());
        assertEquals(GLASS, answer.getKind());
        assertEquals("OK", answer.getStatus());
    }


}
