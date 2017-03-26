package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.Stop;
import fr.unice.polytech.si3.qgl.iaac.air.findIsland.EchoFace;
import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;

/**
 * Created by lehmann on 04/02/17.
 */
public class AirStrategy {

    private static int halfTurn;
    private State state;
    private Drone drone;
    private ReadJSON json;
    private Carte carte;
    private Budget budget;
    private boolean isOver;

    /**
     * default constructor
     * @param drone
     * @param json
     * @param carte
     * @param budget
     */
    public AirStrategy(Drone drone, ReadJSON json, Carte carte, Budget budget) {
        this.drone = drone;
        this.json = json;
        this.carte = carte;
        this.budget = budget;
        state = new EchoFace();
        isOver = false;
        halfTurn = 0;
    }

    /**
     * increase the number of halfTurn
     */
    public static void incHalfTurn() {
        halfTurn++;
    }

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
            state = state.nextState(json);
            carte.addAirCase(drone.getCoord());

            if (carte.hasAcrique() && halfTurn == 1) {
               isOver = true;
            }
        }
    }

    /**
     * @return true if air exploration is over
     */
    public boolean isOver(){
        return isOver;
    }


}
