package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;

/**
 * Created by sebde on 20/03/2017.
 */
public class FactoryTest {
    private Men men;
    private ReadJSON json;
    private Carte carte;
    private Contracts contracts;
    private State state;

    @Before
    public void defineContext() throws IOException{
        men=new Men(new Point(0,0));
        carte=mock(Carte.class);
        json=new ReadJSON();
        json.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        contracts=new Contracts();
        state=new Factory();

    }

    /*@Test
    public void executeFactoryWithARessourceComposedOfOnePrimaryResources(){
        contracts.add(new Contract(20,EnumPrimaryResources.WOOD));
        contracts.getPrimaryContract().add(50);
        contracts.add(new Contract(10,EnumManufacturedResources.PLANK));
        assertEquals("{ \"action\": \"transform\", \"parameters\": { \"WOOD\": 12 }}", state.execute(men,contracts,carte));
    }

    @Test
    public void executeFactoryWithARessourceComposedOFTwoPrimaryResources(){
        contracts.add(new Contract(30,EnumPrimaryResources.SUGAR_CANE));
        contracts.add(new Contract(30,EnumPrimaryResources.FRUITS));
        contracts.getContract().add(50);
        contracts.getContract().add(50);
        contracts.add(new Contract(3,EnumManufacturedResources.RUM));
        assertEquals(state.execute(men,contracts,carte),"{ \"action\": \"transform\", \"parameters\": { \"SUGAR_CANE\": 30, \"FRUITS\": 3 }}");
    }*/
}
