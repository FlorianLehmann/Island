package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.Carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.RIGHT;

/**
 * Created by lehmann on 04/02/17.
 */
public class AirStrategy {

    private State state;
    private Drone drone;
    private EnumDirection lastDirection;
    private ReadJSON json;
    private Carte carte;

    public AirStrategy(Drone drone, ReadJSON json, Carte carte) {
        this.drone = drone;
        this.json = json;
        this.carte = carte;
        state = new State0();//todo clean
        lastDirection = RIGHT;
    }

    public String takeAction() {
        return state.execute(drone);
    }

    public void acknowledgeResults() {
        state = state.wait(json);
    }

    public EnumDirection getLastDirection(){
        return lastDirection;
    }

    public void setLastDirection(EnumDirection lastDirection){
        this.lastDirection=lastDirection;
    }

}
