package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

/**
 * Created by sebde on 04/02/2017.
 */
public class Scan1 implements State {

    public String execute(Drone drone){
        return drone.scan();
    }

    public State nextState(ReadJSON json) {
        if (json.getFound())
            return new Fly3();
        return new Echo2();

    }

}
