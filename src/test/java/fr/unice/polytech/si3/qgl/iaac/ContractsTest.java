package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.contracts.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.FISH;
import static org.junit.Assert.assertEquals;
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
        contracts.add(new PrimaryContract(EnumPrimaryResources.WOOD, 1500));
        assertFalse(contracts.isCompleted());
    }

    @Test
    public void removeTest() {
        contracts.add(new PrimaryContract(EnumPrimaryResources.WOOD, 1500));
        contracts.remove(EnumPrimaryResources.WOOD);
        assertTrue(contracts.isCompleted());
    }

    @Test
    public void SortContractsTest() {
        contracts.add(new PrimaryContract(EnumPrimaryResources.WOOD, 1500));
        contracts.add(new PrimaryContract(FISH, 1500));
        contracts.sortPrimaryContracts();
        assertEquals(FISH, contracts.getContract().getName());
    }

    @Test
    public void SortContractsOrderTest() {
        contracts.add(new PrimaryContract(FISH, 1500));
        contracts.add(new PrimaryContract(EnumPrimaryResources.WOOD, 1500));
        contracts.sortPrimaryContracts();
        assertEquals(FISH, contracts.getContract().getName());
    }

    @Test
    public void SortEmptyContractsTest() {
        try {
            contracts.sortPrimaryContracts();
            assertTrue(true);
        } catch( NullPointerException e) {
            assertFalse(false);
        }
    }

}
