package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 12/02/2017.
 */
public class TourComplet1 implements State {

    private int range=0;

    public TourComplet1(){}

    public TourComplet1(int range){
        this.range=range;
    }

    public String execute(Drone drone){
        return drone.fly();

    }

    public State wait(ReadJSON json){
        return new TourComplet2(range);

    }

    public boolean isOver(){return true;}
}
