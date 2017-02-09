package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lehmann on 04/02/17.
 */
public class ContractsTest {

    private Contracts contracts;

    @Before
    public void defineContext() {
        contracts = new Contracts();
    }

    @Test
    public void addTest() {
        assertTrue(contracts.isCompleted());
        contracts.add(new Contract(EnumPrimaryResources.WOOD, 1500));
        assertFalse(contracts.isCompleted());
    }

    @Test
    public void removeTest() {
        contracts.add(new Contract(EnumPrimaryResources.WOOD, 1500));
        contracts.remove(EnumPrimaryResources.WOOD);
        assertTrue(contracts.isCompleted());
    }
}
