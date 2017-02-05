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
        if(json.getGround()){
            Fly3 next=new Fly3();
            return next;
        }
        else{
            Echo2 next=new Echo2();
            return next;
        }
    }

    public boolean isOver(){
        return true;
    }
}
