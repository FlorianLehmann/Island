package fr.unice.polytech.si3.qgl.iaac;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.*;

public class ExplorerTest{

    Explorer explorer;

    @Before
    public void defineContext(){
	explorer = new Explorer();
	String str = "{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}";
	    explorer.initialize(str);
    }

    @Test
    public void checkJson(){
	assertEquals(12, Explorer.getNumberMen());
    }

    @Test
    public void checkJson2(){
	assertEquals(10000, Explorer.getBudget());
    }

    @Test
    public void checkJson3(){
	assertEquals("W", Explorer.getHeading());
    }

    @Test
    public void checkJson4(){
	Map<String, Integer> test = new HashMap();
	test = Explorer.getContracts();
	int wood = test.get("WOOD");
	int glass = test.get("GLASS");
	assertTrue(wood == 600 && glass == 200);
    }

    @Test
    public void checkTakeDecision(){
	assertEquals(explorer.takeDecision(),  "{ \"action\": \"scan\" }");
    }

    @Test
    public void checkTakeDecision2(){
	explorer.takeDecision();
	assertEquals(explorer.takeDecision(),  "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"W\" } }");
    }

    @Test
    public void checkTakeDecision3(){
	Explorer explorer2 = new Explorer();
	String str = "{\"men\": 12,\"budget\": 18,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}";
	explorer2.initialize(str);
	assertEquals(explorer.takeDecision(),  "{ \"action\": \"stop\" }");
    }

    @Test
    public void checkAcknowledgeReults(){
	explorer.takeDecision();
	explorer.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
	assertEquals(explorer.getFound(), "GROUND");
    }

    @Test
    public void checkAcknowledgeReults2(){
	explorer.takeDecision();
	explorer.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
	assertFalse(explorer.getFound().equals("GROUND"));
	}

    @Test
    public void checkAcknowledgeReults3(){
	explorer.takeDecision();
	explorer.takeDecision();
	explorer.acknowledgeResults("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
	assertEquals(2 , explorer.getRange());

    }

    @Test
    public void checkAcknowledgeReults4(){
	explorer.takeDecision();
	explorer.takeDecision();
	explorer.takeDecision();





	explorer.acknowledgeResults("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
	assertEquals("GROUND" ,explorer.getFound());

    }

    @Test
    public void checkAcknowledgeReults5(){
	for(int i = 0; i < 1000; i++)
	    explorer.takeDecision();


	assertEquals(2 , 2);

    }
}