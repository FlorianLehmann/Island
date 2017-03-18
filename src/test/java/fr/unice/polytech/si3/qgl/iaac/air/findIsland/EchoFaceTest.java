package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.FlyToEarth4;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.*;
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
    public void ini(){
        drone = new Drone(SOUTH);
        echoFace = new EchoFace();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(echoFace.execute(drone),ECHO.toString(SOUTH.toString()));
    }


    @Test
    public void waitWithOceanFace(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(echoFace.wait(read) instanceof EchoLeft);
    }

    @Test
    public void waitWithGroundFace(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(echoFace.wait(read) instanceof FlyToEarth4);
    }

}
