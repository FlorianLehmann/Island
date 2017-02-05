package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 05/02/2017.
 */
public class Fly6 implements State {

    public String execute(Drone drone){
        return drone.fly();
    }

    public State wait(ReadJSON json){
        EchoOpDirection5 next=new EchoOpDirection5();
        return next;
    }

    public boolean isOver(){
        return true;
    }
}
