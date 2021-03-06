package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.LEFT;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.RIGHT;

/**
 * Created by sebde on 04/02/2017.
 */
public class EchoOpDirection5 implements State {

    public String execute(Drone drone){
        if(LEFT.equals(drone.getLastDirection()))
            return drone.echo(RIGHT);
        return drone.echo(LEFT);
    }

    public State nextState(ReadJSON json) {
        if (json.getFound() && json.getRange() < 3)
            return new Fly6();
        return new DemiTour7(0);
    }

}
