package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;

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

    public State nextState(ReadJSON2 json){
        if(nbCase>0)
            return this;
        return new Scan1();
    }

}
