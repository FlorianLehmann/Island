package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 04/02/2017.
 */
public class Scan1 implements State {

    public String execute(Drone drone){
        return drone.scan();
    }

    public State wait(ReadJSON json){
        //todo ajouter cas stop
        if(json.getGround())
            return new Fly3();
        return new Echo2();

    }

}
