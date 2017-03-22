package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.FlyToEarth4;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.ECHO;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Quentin on 11/02/2017.
 */
public class EchoFaceTest {

    private Drone drone;
    private State echoFace;
    private ReadJSON read;

    @Before
    public void ini() throws IOException {
        drone = new Drone(SOUTH);
        echoFace = new EchoFace();
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(echoFace.execute(drone),ECHO.toString(SOUTH.toString()));
    }


    @Test
    public void waitWithOceanFace() throws IOException {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(echoFace.nextState(read) instanceof EchoLeft);
    }

    @Test
    public void waitWithGroundFace() throws IOException {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(echoFace.nextState(read) instanceof FlyToEarth4);
    }

}
