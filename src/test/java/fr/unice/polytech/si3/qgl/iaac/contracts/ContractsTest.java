package fr.unice.polytech.si3.qgl.iaac.contracts;


import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.*;
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
    public void containRessource(){
        contracts.add(new Contract(50,FRUITS));
        assertTrue(contracts.containRessource(FRUITS));
    }

    @Test
    public void notContainRessource(){
        contracts.add(new Contract(50,WOOD));
        assertFalse(contracts.containRessource(QUARTZ));
    }

    @Test
    public void containRessourceWichIsCompleted(){
        contracts.add(new Contract(50,WOOD));
        contracts.getPrimaryContract().add(100);
        assertFalse(contracts.containRessource(WOOD));
        contracts.verifyContractCompleted(contracts.getPrimaryContract());
        assertTrue(contracts.isPrimaryCompleted());
        assertTrue(contracts.isCompleted());
    }

    @Test
    public void getPrimaryContractTestToFindAContractFromARessource(){
        contracts.add(new Contract(60,WOOD));
        contracts.add(new Contract(100,FRUITS));
        assertEquals(FRUITS,contracts.getPrimaryContract(FRUITS).getName());
        contracts.addColectedContract(100,FRUITS);
        assertEquals(WOOD,contracts.getContract().getName());
    }

   @Test
   public void secondaryContractCompleted(){
       contracts.add(new Contract(10,PLANK));
       contracts.getSecondaryContract().add(10);
       contracts.verifyContractCompleted(contracts.getSecondaryContract());
       assertTrue(contracts.isSecondaryCompleted());
       assertTrue(contracts.isCompleted());
   }

    @Test
    public void onlyPrimaryContractCompleted(){
        contracts.add(new Contract(60,WOOD));
        contracts.add(new Contract(100,PLANK));
        contracts.addColectedContract(120,WOOD);
        contracts.verifyContractCompleted(contracts.getPrimaryContract());
        assertFalse(contracts.isSecondaryCompleted());
        assertTrue(contracts.isPrimaryCompleted());
        assertFalse(contracts.isCompleted());
    }

    @Test
    public void transformSecondaryContractToPrimaryContract(){
        contracts.add(new Contract(20,RUM));
        contracts.allocateContracts();
        assertTrue(contracts.containRessource(SUGAR_CANE));
        assertTrue(contracts.containRessource(FRUITS));
    }

    /*@Test
    public void couldCompleteAnotherContratsWithOneSecondary(){
        contracts.add(new Contract(PLANK,10));
        contracts.add(new Contract(WOOD,20));
        contracts.getPrimaryContract().sub(50);
        assertTrue(contracts.couldCompleteAnotherContract());
    }*/

    @Test
    public void couldCompleteAnotherContratsWithoutPrimaryRessource(){
        contracts.add(new Contract(10,GLASS));
        assertFalse(contracts.couldCompleteAnotherContract());
    }

    @Test
    public void couldCompleteAnotherContratsWithoutSecondary(){
        contracts.add(new Contract(100,FRUITS));
        assertFalse(contracts.couldCompleteAnotherContract());
    }

    /*@Test
    public void getManufacturedContractWichIsPossible(){
        Contract contract=new Contract(PLANK,10);
        contracts.add(new Contract(WOOD,10));
        contracts.getPrimaryContract().sub(20);
        contracts.add(contract);
        assertEquals(contracts.getManufacturedContract(),contract);
    }*/

    @Test
    public void priorityPrimaryContactToNotPriority(){
        contracts.add(new Contract(60,WOOD));
        contracts.add(new Contract(100,PLANK));
        contracts.add(new Contract(500,FUR));
        Contract contract = new Contract(500,FUR);
        contracts.changePrimaryContractToNotAPriorityPrimaryContract(contract);
        assertFalse(contracts.contain(FUR));

    }
}
