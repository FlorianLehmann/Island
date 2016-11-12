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


}
