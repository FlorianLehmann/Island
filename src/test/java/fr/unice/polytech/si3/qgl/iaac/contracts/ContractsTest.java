package fr.unice.polytech.si3.qgl.iaac.contracts;


import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.PLANK;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.RUM;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;
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
        contracts.add(new Contract(1500, WOOD));
        contracts.add(new Contract(1500, PLANK));
        assertFalse(contracts.isCompleted());
    }

    @Test
    public void removeTest() {
        contracts.add(new Contract(1500, WOOD));
        contracts.add(new Contract(1500, PLANK));
        contracts.remove(WOOD);
        contracts.remove(PLANK);
        assertTrue(contracts.isCompleted());
    }

    @Test
    public void containRessource() {
        contracts.add(new Contract(50, FRUITS));
        assertTrue(contracts.needResource(FRUITS));
    }

    @Test
    public void notContainRessource() {
        contracts.add(new Contract(50, WOOD));
        assertFalse(contracts.needResource(QUARTZ));
    }

    @Test
    public void containRessourceWichIsCompleted() {
        contracts.add(new Contract(50, WOOD));
        contracts.addColectedContract(50, WOOD);
        assertTrue(contracts.isPrimaryCompleted());
        assertTrue(contracts.isCompleted());
    }

    @Test
    public void onlyPrimaryContractCompleted() {
        contracts.add(new Contract(60, WOOD));
        contracts.add(new Contract(130, PLANK));
        contracts.addColectedContract(130, WOOD);
        assertFalse(contracts.isSecondaryCompleted());
        assertTrue(contracts.isPrimaryCompleted());
        assertFalse(contracts.isCompleted());
    }

    @Test
    public void transformSecondaryContractToPrimaryContract() {
        contracts.add(new Contract(20, RUM));
        contracts.allocateContracts();
        assertTrue(contracts.needResource(SUGAR_CANE));
        assertTrue(contracts.needResource(FRUITS));
    }
}
