package fr.unice.polytech.si3.qgl.iaac;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ground.GroundStrategy;
import fr.unice.polytech.si3.qgl.iaac.ground.Men;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.STOP;


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
     * @param string
     */
    @Override
    public void initialize(String s) {
        readJSON = new ReadJSON(s);
        drone = readJSON.initDrone();
        budget = readJSON.initBudget();
        carte = new Carte(readJSON);
        air = new AirStrategy(drone, readJSON, carte, budget);
        contracts = readJSON.initContracts();
        contracts.allocateContracts();
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
        try {
            if (!air.isOver())
                return air.takeAction();
            return ground.takeAction();
        } catch (RuntimeException ex) {
            return STOP.toString("");
        }
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
