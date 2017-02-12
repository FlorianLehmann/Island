package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 12/02/2017.
 */
public class TourComplet2 implements State {

private int range;

    public TourComplet2(){}

    public TourComplet2(int range){
        this.range=range;
    }

    public String execute(Drone drone){
        return drone.echo(drone.getLastDirection());

    }

    public State wait(ReadJSON json){
        range=json.getRange();
        if(json.getGround() && range<3){
            return new TourComplet1(range);
        }
        return new TourComplet3(range);

    }

    public boolean isOver(){return true;}
}
