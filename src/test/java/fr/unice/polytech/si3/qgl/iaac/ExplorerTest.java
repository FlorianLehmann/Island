package fr.unice.polytech.si3.qgl.iaac;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ExplorerTest {

    Explorer exp;

    @Before
    public void defineContext() {
        exp = new Explorer();
        exp.initialize("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, { \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
    }

    @Test
    public void FinalReportTest() {
        assertEquals("Report", exp.deliverFinalReport());
    }

    @Test
    public void TakeDecisionTest() {
        assertEquals(exp.takeDecision() , "{ \"action\": \"echo\", \"parameters\": { \"direction\":\"W\" } }");
    }


}
