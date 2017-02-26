package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.Budget;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ground.GroundStrategy;
import fr.unice.polytech.si3.qgl.iaac.ground.Men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;


import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.LAND;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.STOP;

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
    public void defineContext() {
        men = new Men(new Point(0,0));
        json = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        Carte carte = mock(Carte.class);
        when(carte.getCreekID()).thenReturn("ID");
        budget = new Budget(1000);
        strategy = new GroundStrategy(8, json, men, carte, budget, new Contracts());
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

    @Test
    public void acknowledgeResultsShouldChangeState() {
        assertEquals(LAND.toString("ID", 7) ,strategy.takeAction());
        strategy.acknowledgeResults();
        assertNotEquals(LAND.toString("ID", 7) ,strategy.takeAction());
    }


}
