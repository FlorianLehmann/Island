package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.EST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by lehmann on 11/02/17.
 */
public class AirStrategyTest {

    private AirStrategy strategy;
    private Drone drone;
    private ReadJSON json;
    private Carte carte;
    private Budget budget;

    @Before
    public void defineContext() throws IOException {
        drone = new Drone(EST);
        json = new ReadJSON();
        json.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        carte = new Carte(json);
        budget = new Budget(1000);
        strategy = new AirStrategy(drone, json, carte, budget);
    }

    @Test
    public void takeAction() {
        assertEquals(ECHO.toString(EST.toString()) ,strategy.takeAction());
    }

    @Test
    public void takeActionWithoutBudget() {
        budget.subBudget(800);
        assertNotEquals(ECHO.toString(EST.toString()) ,strategy.takeAction());
    }

    @Test
    public void acknowledgeResultsChangeState() {
        assertEquals(ECHO.toString(EST.toString()) ,strategy.takeAction());
        strategy.acknowledgeResults();
        assertNotEquals(ECHO.toString(EST.toString()) ,strategy.takeAction());
    }
}
