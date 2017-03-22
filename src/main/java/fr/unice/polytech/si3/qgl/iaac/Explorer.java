package fr.unice.polytech.si3.qgl.iaac;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ground.GroundStrategy;
import fr.unice.polytech.si3.qgl.iaac.ground.Men;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;


import java.awt.*;
import java.io.IOException;


public class Explorer implements IExplorerRaid {

    /**
     * Attributes
     */
    private ReadJSON2 readJSON;
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
        readJSON = new ReadJSON2();
        try {
            readJSON.read(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drone = new Drone(readJSON.getAnswer().getHeading());
        budget = new Budget(readJSON.getAnswer().getBudget());
        carte = new Carte(readJSON);
        air = new AirStrategy(drone, readJSON, carte, budget);
        contracts = new Contracts(readJSON.getAnswer().getContracts());
        nbMen = readJSON.getAnswer().getMen();
        men = new Men(new Point(0,0));
        ground = new GroundStrategy(nbMen, readJSON, men, carte, budget, contracts );
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
        try {
            readJSON.read(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
