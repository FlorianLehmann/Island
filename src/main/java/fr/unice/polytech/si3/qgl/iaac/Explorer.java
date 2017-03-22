package fr.unice.polytech.si3.qgl.iaac;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ground.GroundStrategy;
import fr.unice.polytech.si3.qgl.iaac.ground.Men;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.awt.*;


public class Explorer implements IExplorerRaid {

    /**
     * Attributes
     */
    private ReadJSON readJSON;
    private Drone drone;
    private Budget budget;
    private AirStrategy air;
    private Men men;
    private GroundStrategy ground;
    private Carte carte;
    private Contracts contracts;
    private int nbMen;

    /**
     * Initialize attributes with the JSON request
     * @param s
     */
    @Override
    public void initialize(String s) {
        readJSON = new ReadJSON(s);
        drone = readJSON.initDrone();
        budget = readJSON.initBudget();
        carte = new Carte(readJSON);
        air = new AirStrategy(drone, readJSON, carte, budget);
        contracts = readJSON.initContracts();
        nbMen = readJSON.initNbMen();
        men = new Men(new Point(0,0));
        ground = new GroundStrategy(readJSON.initNbMen(), readJSON, men, carte, budget, contracts );
    }

    /**
     * Take decision
     * @return
     */
    @Override
    public String takeDecision() {
        if (!air.isOver())
            return air.takeAction();
        return ground.takeAction();
    }

    /**
     * result of the inquiry
     * @param s
     */
    @Override
    public void acknowledgeResults(String s) {
        readJSON.read(s);
        if (!air.isOver()) {
            air.acknowledgeResults();
            if (air.isOver()) {
                men = new Men(carte.getACreek());
                ground = new GroundStrategy(nbMen, readJSON, men, carte, budget, contracts);
            }
        }
        else {
            ground.acknowledgeResults();
        }

    }

    /**
     * deliver the final report
     * @return
     */
    @Override
    public String deliverFinalReport() {
        return "REPORT";
    }
}
