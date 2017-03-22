package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 05/02/2017.
 */
public class Fly6 implements State {

    public String execute(Drone drone){
        return drone.fly();
    }

    public State nextState(ReadJSON json){
        return new EchoOpDirection5();
    }

}
