package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.GLASS;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.PLANK;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 26/03/2017.
 */
public class ContractsStrategyTest {

    private Contracts contracts;
    private ContractsStrategy contractsStrategy;
    private Carte carte;
    private ReadJSON readJSON;

    @Before
    public void init(){
        contracts = new Contracts();
        readJSON = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        carte = new Carte(readJSON);
        contractsStrategy = new ContractsStrategy(contracts, carte);

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

    @Test
    public void ShouldGetAnotherContracts() {
        contracts.add(new Contract(100, WOOD));
        contracts.add(new Contract(60000, FISH));
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"MANGROVE\", \"ALPINE\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
        carte.addAirCase(new Point(3, 3));
        contractsStrategy.nextContract(10000);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldntGetAnotherContracts() {
        contracts.add(new Contract(100, WOOD));
        contracts.add(new Contract(60000, FISH));
        contractsStrategy.nextContract(10000);
    }

}
