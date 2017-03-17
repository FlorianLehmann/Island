package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.Budget;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
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
    private boolean isOver;

    //temp todo
    private static int demitour;



    public static void incDemitour() {
        demitour++;
    }

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
        isOver = false;
        demitour = 0;
    }

    //todo condition d'arrêt

    /**
     * take an action
     * @return
     */
    public String takeAction() {
        if (budget.hasBudget())
            return state.execute(drone);
        return (new Stop().execute(drone));
    }

    /**
     * Analyse results
     */
    public void acknowledgeResults() {
        if (budget.hasBudget()){
            budget.subBudget(json.getCost());
            state = state.wait(json);
            carte.addAirCase(drone.getCoord());

            if (carte.tmp_hasAcrique() && demitour == 1) {
               isOver = true;
            }
        }
    }

    public boolean isOver(){
        return isOver;
    }


}
