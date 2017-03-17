package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by lehmann on 09/02/17.
 */
public class Stop implements State {
    public String execute(Drone drone){
        return STOP.toString("");
    }

    public State wait(ReadJSON json){
        throw new UnsupportedOperationException();
    }

}
