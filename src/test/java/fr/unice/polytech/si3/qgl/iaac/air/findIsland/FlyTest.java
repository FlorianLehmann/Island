package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.FLY;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.SOUTH;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.WEST;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 11/02/2017.
 */
public class FlyTest {

    private Drone drone;
    private State fly;
    private ReadJSON read;

    @Before
    public void ini(){
        drone = new Drone(SOUTH);
        fly = new Fly();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(fly.execute(drone),FLY.toString(SOUTH.toString()));
    }


    @Test
    public void waitTest(){
        read.read("{ \"action\": \"fly\" }");
        assertTrue(fly.wait(read) instanceof EchoLeft);
    }

    @Test
    public  void isOverTest(){
        fly.execute(drone);
        assertEquals(fly.isOver(),true);
    }

}
