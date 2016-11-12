
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
        assertEquals(drone.left("N"),"E");
    }

    @Test
    public void leftS (){
        drone =new Drone();
        assertEquals(drone.left("S"),"W");
    }

    @Test
    public void leftE (){
        drone =new Drone();
        assertEquals(drone.left("E"),"S");
    }

    @Test
    public void leftW (){
        drone =new Drone();
        assertEquals(drone.left("W"),"N");
    }

    @Test
    public void rightN (){
        drone =new Drone();
        assertEquals(drone.right("N"),"W");
    }

    @Test
    public void rightS (){
        drone =new Drone();
        assertEquals(drone.right("S"),"E");
    }

    @Test
    public void rightE (){
        drone =new Drone();
        assertEquals(drone.right("E"),"N");
    }

    @Test
    public void rightW (){
        drone =new Drone();
        assertEquals(drone.right("W"),"S");
    }

    @Test
    public void findIslandEtat0N(){
        drone =new Drone("N");
        drone.findIsland();
        assertEquals(drone.getAction(),"{ \"action\": \"scan\" }");
    }

    @Test
    public void findIslandEtat2N(){
        drone =new Drone("N","",10);
        drone.findIsland();
        drone.setResult("GROUND");
        drone.findIsland();
        assertEquals(drone.getAction(),"{ \"action\": \"stop\" }");
    }
}
