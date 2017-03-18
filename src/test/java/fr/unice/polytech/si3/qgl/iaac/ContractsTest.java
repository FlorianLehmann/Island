package fr.unice.polytech.si3.qgl.iaac;


import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.contracts.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaac.contracts.SecondaryContract;
import org.junit.Before;
import org.junit.Ignore;
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
        assertEquals(FRUITS,contracts.getPrimaryContract(FRUITS).getName());
    }

}
