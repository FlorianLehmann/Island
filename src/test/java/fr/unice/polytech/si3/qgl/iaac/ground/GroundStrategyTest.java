package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.LAND;
import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.STOP;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.WOOD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by florian on 19/02/2017.
 */
public class GroundStrategyTest {

    private GroundStrategy strategy;
    private Men men;
    private ReadJSON json;
    private Carte carte;
    private Budget budget;


    @Before
    public void defineContext() throws IOException {
        men = new Men(new Point(0,0));
        json = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");;
        json.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        Carte carte = mock(Carte.class);
        when(carte.hasResource(WOOD)).thenReturn(true);
        when(carte.getCreekID()).thenReturn("ID");
        budget = new Budget(2000);
        Contracts contracts = new Contracts();
        contracts.add(new Contract(100, WOOD));
        strategy = new GroundStrategy(8, json, men, carte, budget, contracts);
    }

    @Test
    public void shouldLand() {
        assertEquals(LAND.toString("ID", 7) ,strategy.takeAction());
    }

    @Test
    public void takeActionWithoutBudget() {
        budget.subBudget(800);
        assertEquals(STOP.toString("") ,strategy.takeAction());
    }

    @Ignore
    @Test
    public void acknowledgeResultsShouldChangeState() {
        assertEquals(LAND.toString("ID", 7) ,strategy.takeAction());
        strategy.acknowledgeResults();
        assertNotEquals(LAND.toString("ID", 7) ,strategy.takeAction());
    }


}
