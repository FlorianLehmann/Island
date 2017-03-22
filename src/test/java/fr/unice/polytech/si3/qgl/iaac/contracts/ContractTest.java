package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.exceptions.NoAmountContractException;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.FISH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lehmann on 04/02/17.
 */
public class ContractTest {

    private Contract contract;

    @Before
    public void defineContext() {
        contract = new Contract(EnumPrimaryResources.WOOD, 1500);
    }

    @Test
    public void defineBadContextTest() {
        try {
            new Contract(EnumPrimaryResources.WOOD, 0);
            assertTrue(false);
        }
        catch (NoAmountContractException exception)
        {
            assertTrue(true);
        }
        try {
            new Contract(EnumPrimaryResources.WOOD, -10);
            assertTrue(false);
        }
        catch (NoAmountContractException exception)
        {
            assertTrue(true);
        }
        try {
            new Contract(EnumPrimaryResources.WOOD, 5);
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
        assertEquals(15, contract.getDebt());
    }

    @Test
    public void subAmountTest() {
        contract.add(150);
        contract.sub(15);
        assertEquals(135, contract.getDebt());
        contract.sub(1500);
        assertEquals(0, contract.getDebt());
    }

    @Test
    public void isCompletedTest(){
        contract.add(1500);
        assertTrue(contract.isCompleted());
    }

    @Test
    public void getNameTest() {
        assertEquals(EnumPrimaryResources.valueOf("FISH"), new Contract(FISH, 1500).getName());
    }

    @Test
    public void shouldIncreaseNumberOfResourcesRequired() {
        contract.addRequired(500);
        assertEquals(2000, contract.getRequired());
    }

}
