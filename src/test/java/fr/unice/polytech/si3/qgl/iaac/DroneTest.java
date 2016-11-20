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

    //----------------------------------------------------------------
    //Test des états de parcourirIle

    @Test
    public void testPiEtat0(){
        drone =new Drone("N");
        drone.piEtat0();
        assertEquals(drone.getAction(),"{ \"action\": \"scan\" }");
    }

    @Test
    public void testPiEtat2(){
        drone =new Drone("N");
        drone.piEtat2();
        assertEquals(drone.getAction(),"{ \"action\": \"echo\", \"parameters\": { \"direction\": \"N\" } }");
    }

    @Test
    public void testPiEtat4(){
        drone =new Drone("N");
        drone.piEtat4();
        assertEquals(drone.getAction(),"{ \"action\": \"fly\" }");
    }

    @Test
    public void testPiEtat100(){
        drone =new Drone("N");
        drone.piEtat100();
        assertEquals(drone.getAction(),"{ \"action\": \"stop\" }");
    }

    @Test
    public void testPiEtat10(){
        drone =new Drone("N");
        drone.piEtat10();
        assertEquals(drone.getAction(),"{ \"action\": \"echo\", \"parameters\": { \"direction\": \"N\" } }");
    }

    @Test
    public void testPiEtat12(){
        drone =new Drone("N");
        drone.piEtat12();
        drone.setNbCase(10);
        assertEquals(drone.getAction(),"{ \"action\": \"fly\" }");
    }

    @Test
    public void testPiEtat12NbCase0(){
        drone =new Drone("N");
        drone.piEtat12();
        drone.setNbCase(0);
        assertTrue(drone.getEtat()==0);
    }

    @Test
    public void testPiEtat5(){
        drone =new Drone("N");
        drone.piEtat5();
        String direction1=drone.left("N");
        assertEquals(drone.getAction(),"{ \"action\": \"echo\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }");
    }

    @Test
    public void testPiEtat7(){
        drone =new Drone("N");
        drone.piEtat7();
        assertEquals(drone.getAction(),"{ \"action\": \"fly\" }");
    }

    /*@Test
    public void testPiEtat8(){
        drone =new Drone("N");
        drone.piEtat8();
        String direction1=drone.right("N");
        assertEquals(drone.getAction(),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }");
    }

    @Test
    public void testPiEtat9(){
        drone =new Drone("N");
        drone.piEtat9();
        String direction1=drone.right("N");
        assertEquals(drone.getAction(),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }");
    }*/

    @Test
    public void testPiEtat13(){
        drone =new Drone("N");
        drone.piEtat13();
        String direction1=drone.right("N");
        assertEquals(drone.getAction(),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }");
    }

    /*@Test
    public void testPiEtat14(){
        drone =new Drone("N");
        drone.piEtat14();
        String direction1=drone.right("N");
        assertEquals(drone.getAction(),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }");
    }

    @Test
    public void testPiEtat15(){
        drone =new Drone("N");
        drone.piEtat15();
        String direction1=drone.right("N");
        assertEquals(drone.getAction(),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }");
    }

    @Test
    public void testPiEtat16(){
        drone =new Drone("N");
        drone.piEtat16();
        assertEquals(drone.getAction(),"{ \"action\": \"fly\" }");
    }

    @Test
    public void testPiEtat17(){
        drone =new Drone("N");
        drone.piEtat17();
        String direction1=drone.right("N");
        assertEquals(drone.getAction(),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"" + direction1 + "\" } }");
    }*/

    @Test
    public void testPiEtat1(){
        drone =new Drone("N");
        drone.piEtat1();
        assertTrue(drone.getEtat()==2);
    }

    @Test
    public void testPiEtat1Bis(){
        drone =new Drone("N");
        drone.setIdCrique("1");
        drone.setIdPU("2");
        drone.piEtat1();
        assertTrue(drone.getEtat()==100);
    }

    @Test
    public void testPiEtat3(){
        drone =new Drone("N");
        drone.setResult("OCEAN");
        drone.setLastGround(false);
        drone.piEtat3();
        assertTrue(drone.getEtat()==5);
    }

    @Test
    public void testPiEtat3Bis(){
        drone =new Drone("N");
        drone.setResult("GROUND");
        drone.piEtat3();
        assertTrue(drone.getEtat()==4);
    }

    @Test
    public void testPiEtat11(){
        drone =new Drone("N");
        drone.setResult("GROUND");
        drone.piEtat11();
        assertTrue(drone.getEtat()==12);
    }

    @Test
    public void testPiEtat11Bis(){
        drone =new Drone("N");
        drone.setResult("OCEAN");
        drone.piEtat11();
        assertTrue(drone.getEtat()==13);
    }

    /*@Test
    public void testPiEtat6(){
        drone =new Drone("N");
        drone.setResult("GROUND");
        drone.setNbCase(1);
        drone.piEtat6();
        assertTrue(drone.getEtat()==7);
    }*/

    /*@Test
    public void testPiEtat6Bis(){
        drone =new Drone("N");
        drone.setResult("OCEAN");
        drone.piEtat6();
        assertTrue(drone.getEtat()==8);
    }*/
}
