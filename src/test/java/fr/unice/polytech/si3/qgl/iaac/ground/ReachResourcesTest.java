package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.WOOD;
import static org.junit.Assert.assertEquals;

/**
 * Created by florian on 26/03/2017.
 */
public class ReachResourcesTest {

    private ReachResources reachResources;
    private Carte carte;
    private ReadJSON readJSON;
    private Men men;
    private Contracts contracts;
    private Budget budget;

    @Before
    public void defineContext() {
        readJSON = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        carte = new Carte(readJSON);
        men = new Men(new Point(2, 3));
        contracts = new Contracts();
        contracts.add(new Contract(200, WOOD));
        budget = new Budget(3000);
        reachResources = new ReachResources(carte);
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"MANGROVE\", \"ALPINE\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
        carte.addAirCase(new Point(3, 3));
    }

    @Ignore
    @Test
    public void ShouldMove() {
        assertEquals(men.moveTo(EnumOrientation.WEST), reachResources.execute(men, contracts, carte, budget));
    }

}
