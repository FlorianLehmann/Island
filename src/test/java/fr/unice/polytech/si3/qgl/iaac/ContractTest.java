package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.exceptions.NoAmountContractException;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lehmann on 04/02/17.
 */
public class ContractTest {

    private Contract contract;

    @Before
    public void defineContext() {
        contract = new Contract(EnumResources.WOOD, 1500);
    }

    @Test
    public void defineBadContextTest() {
        try {
            new Contract(EnumResources.WOOD, 0);
            assertTrue(false);
        }
        catch (NoAmountContractException exception)
        {
            assertTrue(true);
        }
        try {
            new Contract(EnumResources.WOOD, -10);
            assertTrue(false);
        }
        catch (NoAmountContractException exception)
        {
            assertTrue(true);
        }
        try {
            new Contract(EnumResources.WOOD, 5);
            assertTrue(true);
        }
        catch (NoAmountContractException exception)
        {
            assertTrue(false);
        }
    }

    @Test
    public void addAmountTest() {
        contract.add(15);
        assertEquals(1515, contract.getAmount());
    }

    @Test
    public void subAmountTest() {
        contract.sub(15);
        assertEquals(1485, contract.getAmount());
        contract.sub(1500);
        assertEquals(0, contract.getAmount());
    }

    @Test
    public void isCompletedTest(){
        contract.sub(1500);
        assertTrue(contract.isCompleted());
    }


}