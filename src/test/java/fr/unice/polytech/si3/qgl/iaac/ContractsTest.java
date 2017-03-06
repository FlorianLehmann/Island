package fr.unice.polytech.si3.qgl.iaac;


import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.contracts.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaac.contracts.SecondaryContract;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.LEATHER;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.PLANK;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;
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
        contracts.add(new PrimaryContract(WOOD, 1500));
        contracts.add(new SecondaryContract(PLANK, 1500));
        assertFalse(contracts.isCompleted());
    }

    @Test
    public void removeTest() {
        contracts.add(new PrimaryContract(WOOD, 1500));
        contracts.add(new SecondaryContract(PLANK, 1500));
        contracts.remove(WOOD);
        contracts.remove(PLANK);
        assertTrue(contracts.isCompleted());
    }

    @Test
    public void SortContractsTest() {
        contracts.add(new PrimaryContract(WOOD, 1500));
        contracts.add(new PrimaryContract(FISH, 1500));
        contracts.sortPrimaryContracts(10000);
        assertEquals(FISH, contracts.getPrimaryContract().getName());
    }

    @Test
    public void SortContractsSmallBudgetTest() {
        contracts.add(new PrimaryContract(WOOD, 1000));
        contracts.add(new PrimaryContract(FISH, 5000));
        contracts.sortPrimaryContracts(8000);
        assertEquals(WOOD, contracts.getPrimaryContract().getName());
    }

    @Test
    public void SortContractsOrderTest() {
        contracts.add(new PrimaryContract(FISH, 1500));
        contracts.add(new PrimaryContract(WOOD, 1500));
        contracts.sortPrimaryContracts(10000);
        assertEquals(FISH, contracts.getPrimaryContract().getName());
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
        contracts.add(new SecondaryContract(LEATHER, 1500));
        contracts.add(new SecondaryContract(PLANK, 1500));
        contracts.sortSecondaryContracts(10000);
        assertEquals(PLANK, contracts.getSecondaryContract().getName());
    }

    @Test
    public void SortSecondaryContractsSmallBudgetTest() {
        contracts.add(new SecondaryContract(LEATHER, 1000));
        contracts.add(new SecondaryContract(PLANK, 5000));
        contracts.sortSecondaryContracts(8000);
        assertEquals(LEATHER, contracts.getSecondaryContract().getName());
    }

    @Test
    public void SortSecondaryContractsOrderTest() {
        contracts.add(new SecondaryContract(PLANK, 1500));
        contracts.add(new SecondaryContract(LEATHER, 1500));
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

    @Test
    public void containRessource(){
        contracts.add(new PrimaryContract(FRUITS,50));
        assertTrue(contracts.containRessource(FRUITS));
    }
    @Test
    public void notContainRessource(){
        contracts.add(new PrimaryContract(WOOD,50));
        assertFalse(contracts.containRessource(QUARTZ));
    }

    @Test
    public void containRessourceWichIsCompleted(){
        contracts.add(new PrimaryContract(WOOD,50));
        contracts.getPrimaryContract().sub(100);
        assertFalse(contracts.containRessource(WOOD));
    }

    @Test
    public void getPrimaryContractTestToFindAContractFromARessource(){
        contracts.add(new PrimaryContract(WOOD,60));
        contracts.add(new PrimaryContract(FRUITS,100));
        assertEquals(WOOD,contracts.getPrimaryContract().getName());
    }

}
