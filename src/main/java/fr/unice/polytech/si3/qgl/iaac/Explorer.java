package fr.unice.polytech.si3.qgl.iaac;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaac.Carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;


public class Explorer implements IExplorerRaid {

    /**
     * Attributes
     */
    private ReadJSON readJSON;
    private Drone drone;
    private Budget budget;
    private AirStrategy air;
    private Carte carte;

    //todo dans airstrategy stopper lorsque le budget est torp faible

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
    }

    /**
     * Take decision
     * @return
     */
    @Override
    public String takeDecision() {
        return air.takeAction();
    }

    /**
     * result of the inquiry
     * @param s
     */
    @Override
    public void acknowledgeResults(String s) {
        readJSON.read(s);
        air.acknowledgeResults();
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
