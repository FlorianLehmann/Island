package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 12/02/2017.
 */
public class TourComplet1 implements State {

    public String execute(Drone drone){
        return drone.heading(drone.getLastDirection());

    }

    public State wait(ReadJSON json){
        return new TourComplet2();

    }

    public boolean isOver(){return true;}
}
