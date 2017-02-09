package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 04/02/2017.
 */
public class FlyToEarth4 implements State {

    private int nbCase;

    public FlyToEarth4(int nbCase){
        this.nbCase=nbCase;
    }

    public String execute(Drone drone){
        nbCase--;
        return drone.fly();
    }

    public State wait(ReadJSON json){
        if(nbCase>0)
            return this;
        return new Scan1();
    }

    public boolean isOver(){
        return true;
    }
}
