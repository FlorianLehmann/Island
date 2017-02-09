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
    /**
     * Initialize attributes with the JSON request
     * @param s
     */
    @Override
    public void initialize(String s) {
        readJSON = new ReadJSON(s);
        drone = readJSON.initDrone();
        budget = readJSON.initBudget();
        carte = new Carte();//todo a un argument
        air = new AirStrategy(drone, readJSON, carte);
    }

    /**
     * Take decision
     * @return
     */
    @Override
    public String takeDecision() {
        return null;
    }

    /**
     * result of the inquiry
     * @param s
     */
    @Override
    public void acknowledgeResults(String s) {

    }

    /**
     * deliver the final report
     * @return
     */
    @Override
    public String deliverFinalReport() {
        return null;
    }
}
