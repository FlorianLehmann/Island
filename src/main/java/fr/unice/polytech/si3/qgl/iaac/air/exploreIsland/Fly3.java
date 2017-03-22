package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;

/**
 * Created by sebde on 04/02/2017.
 */
public class Fly3 implements State {

    public String execute(Drone drone){
        return drone.fly();
    }

    public State nextState(ReadJSON2 json){
        return new Scan1();
    }

}
