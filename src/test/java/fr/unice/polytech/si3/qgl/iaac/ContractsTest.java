package fr.unice.polytech.si3.qgl.iaac;


import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.LEATHER;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.PLANK;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.FISH;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.WOOD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lehmann on 04/02/17.
 */
public class ContractsTest {
/*
    private Contracts contracts;

    @Before
    public void defineContext() {
        contracts = new Contracts();
    }

    @Test
    public void addTest() {
        assertTrue(contracts.isCompleted());
        contracts.add(new Contract(WOOD, 1500));
        contracts.add(new Contract(PLANK, 1500));
        assertFalse(contracts.isCompleted());
    }

    @Test
    public void removeTest() {
        contracts.add(new Contract(WOOD, 1500));
        contracts.add(new Contract(PLANK, 1500));
        contracts.remove(WOOD);
        contracts.remove(PLANK);
        assertTrue(contracts.isCompleted());
    }

    @Test
    public void SortContractsTest() {
        contracts.add(new Contract(WOOD, 1500));
        contracts.add(new Contract(FISH, 1500));
        contracts.sortPrimaryContracts(10000);
        assertEquals(FISH, contracts.getContract().getName());
    }

    @Test
    public void SortContractsSmallBudgetTest() {
        contracts.add(new Contract(WOOD, 1000));
        contracts.add(new Contract(FISH, 5000));
        contracts.sortPrimaryContracts(8000);
        assertEquals(WOOD, contracts.getContract().getName());
    }

    @Test
    public void SortContractsOrderTest() {
        contracts.add(new Contract(FISH, 1500));
        contracts.add(new Contract(WOOD, 1500));
        contracts.sortPrimaryContracts(10000);
        assertEquals(FISH, contracts.getContract().getName());
    }

    @Test
    public void SortEmptyContractsTest() {
        try {
            contracts.sortPrimaryContracts(10000);
            assertTrue(true);
        } catch( NullPointerException e) {
            assertFalse(false);
        }
    }

    @Test
    public void SortSecondaryContractsTest() {
        contracts.add(new Contract(LEATHER, 1500));
        contracts.add(new Contract(PLANK, 1500));
        contracts.sortSecondaryContracts(10000);
        assertEquals(PLANK, contracts.getSecondaryContract().getName());
    }

    @Test
    public void SortSecondaryContractsSmallBudgetTest() {
        contracts.add(new Contract(LEATHER, 1000));
        contracts.add(new Contract(PLANK, 5000));
        contracts.sortSecondaryContracts(8000);
        assertEquals(LEATHER, contracts.getSecondaryContract().getName());
    }

    @Test
    public void SortSecondaryContractsOrderTest() {
        contracts.add(new Contract(PLANK, 1500));
        contracts.add(new Contract(LEATHER, 1500));
        contracts.sortSecondaryContracts(10000);
        assertEquals(PLANK, contracts.getSecondaryContract().getName());
    }

    @Test
    public void SortSecondaryEmptyContractsTest() {
        try {
            contracts.sortSecondaryContracts(10000);;
            assertTrue(true);
        } catch( NullPointerException e) {
            assertFalse(false);
        }
    }
*/
}
