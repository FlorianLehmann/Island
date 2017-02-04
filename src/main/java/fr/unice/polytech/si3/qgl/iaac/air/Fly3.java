package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

/**
 * Created by sebde on 04/02/2017.
 */
public class Fly3 implements State{

    public String execute(Drone drone){
        return drone.fly();
    }

    public State wait(ReadJSON json){
       Scan1 next=new Scan1();
        return next;
    }

    public boolean isOver(){
        return true;
    }
}
