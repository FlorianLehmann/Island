package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.Budget;
import fr.unice.polytech.si3.qgl.iaac.Carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.Stop;
import fr.unice.polytech.si3.qgl.iaac.air.findIsland.EchoFace;

/**
 * Created by lehmann on 04/02/17.
 */
public class AirStrategy {

    private State state;
    private Drone drone;
    private ReadJSON json;
    private Carte carte;
    private Budget budget;

    /**
     * default constructor
     * @param drone
     * @param json
     * @param carte
     * @param budget
     */
    public AirStrategy(Drone drone, ReadJSON json, Carte carte,Budget budget) {
        this.drone = drone;
        this.json = json;
        this.carte = carte;
        this.budget = budget;
        state = new EchoFace();
    }

    //todo condition d'arrêt

    /**
     * take an action
     * @return
     */
    public String takeAction() {
        if (!carte.tmp_hasAcrique() && budget.hasBudget())
            return state.execute(drone);
        return (new Stop().execute(drone));
    }

    /**
     * Analyse results
     */
    public void acknowledgeResults() {
        budget.subBudget(json.getCost());
        state = state.wait(json);
        carte.addAirCase(drone.getCoord());
    }

}
