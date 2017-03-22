package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.*;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;

/**
 * Created by lehmann on 09/02/17.
 */
public class Stop implements State {
    public String execute(Drone drone){
        return STOP.toString("");
    }

    public State nextState(ReadJSON2 json){
        return this;
    }

}
