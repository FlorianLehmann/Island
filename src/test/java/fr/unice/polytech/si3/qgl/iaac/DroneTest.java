package fr.unice.polytech.si3.qgl.iaac;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Quentin on 11/11/2016.
 */
public class DroneTest {

    private Drone drone;

    @Test
    public void leftN (){
        drone =new Drone();
        assertEquals(drone.left("N"),"W");
    }

    @Test
    public void leftS (){
        drone =new Drone();
        assertEquals(drone.left("S"),"E");
    }

    @Test
    public void leftE (){
        drone =new Drone();
        assertEquals(drone.left("E"),"N");
    }

    @Test
    public void leftW (){
        drone =new Drone();
        assertEquals(drone.left("W"),"S");
    }

    @Test
    public void rightN (){
        drone =new Drone();
        assertEquals(drone.right("N"),"E");
    }

    @Test
    public void rightS (){
        drone =new Drone();
        assertEquals(drone.right("S"),"W");
    }

    @Test
    public void rightE (){
        drone =new Drone();
        assertEquals(drone.right("E"),"S");
    }

    @Test
    public void rightW (){
        drone =new Drone();
        assertEquals(drone.right("W"),"N");
    }

    @Test
    public void findIslandEtat0N(){
        drone =new Drone("N");
        drone.findIsland();
        assertEquals(drone.getAction(),"{ \"action\": \"scan\" }");
    }

}
