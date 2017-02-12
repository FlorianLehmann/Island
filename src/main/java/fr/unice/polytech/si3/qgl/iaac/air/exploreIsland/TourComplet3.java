package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 12/02/2017.
 */
public class TourComplet3 implements State {

    private int range;

    public TourComplet3(){}

    public TourComplet3(int range){
        this.range=range;
    }

    public String execute(Drone drone){
        return drone.heading(drone.getLastDirection());

    }

    public State wait(ReadJSON json){
        if(range!=0) {
            return new TourComplet4();
        }
        return new TourComplet5();

    }

    public boolean isOver(){return true;}
}
