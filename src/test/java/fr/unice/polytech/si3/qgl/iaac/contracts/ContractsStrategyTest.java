package fr.unice.polytech.si3.qgl.iaac.contracts;

import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.GLASS;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.PLANK;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.FRUITS;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.WOOD;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 26/03/2017.
 */
public class ContractsStrategyTest {
    private Contracts contracts;
    private ContractsStrategy contractsStrategy;
    @Before
    public void init(){
        contracts = new Contracts();
        contractsStrategy=new ContractsStrategy(contracts);

    }

    @Test
    public void couldCompleteAnotherContratsWithOneSecondary(){
        contracts.add(new Contract(20,PLANK));
        contracts.add(new Contract(20,WOOD));
        contracts.getPrimaryContracts(WOOD).add(50);
        assertTrue(contractsStrategy.couldCompleteAnotherContract());
    }

    @Test
    public void couldCompleteAnotherContratsWithoutPrimaryRessource(){
        contracts.add(new Contract(10,GLASS));
        assertFalse(contractsStrategy.couldCompleteAnotherContract());
    }

    @Test
    public void couldCompleteAnotherContratsWithoutSecondary(){
        contracts.add(new Contract(100,FRUITS));
        assertFalse(contractsStrategy.couldCompleteAnotherContract());
    }

    @Test
    public void getManufacturedContractWichIsPossible(){
        Contract contract=new Contract(10,PLANK);
        contracts.add(new Contract(10,WOOD));
        contracts.getPrimaryContracts(WOOD).add(20);
        contracts.add(contract);
        assertEquals(contractsStrategy.getManufacturedContract(),contract);
    }

}
