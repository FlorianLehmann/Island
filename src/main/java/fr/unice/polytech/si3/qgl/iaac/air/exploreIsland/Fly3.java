package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 04/02/2017.
 */
public class Fly3 implements State {

    public String execute(Drone drone){
        return drone.fly();
    }

    public State wait(ReadJSON json){
       Scan1 next=new Scan1();
        return next;
    }

}
