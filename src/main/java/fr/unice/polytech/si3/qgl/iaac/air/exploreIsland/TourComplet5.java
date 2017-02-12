package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 12/02/2017.
 */
public class TourComplet5 implements State {
    public String execute(Drone drone){
        return drone.heading(drone.getLastDirection());

    }

    public State wait(ReadJSON json){
        AirStrategy.incDemitour();
        return new EchoFront8();


    }

    public boolean isOver(){return true;}
}
