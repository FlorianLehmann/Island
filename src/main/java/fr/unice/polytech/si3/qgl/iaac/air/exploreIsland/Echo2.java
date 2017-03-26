package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.FRONT;

/**
 * Created by sebde on 04/02/2017.
 */
public class Echo2 implements State {

    public String execute(Drone drone){
        return drone.echo(FRONT);
    }

    public State nextState(ReadJSON json){
        if(json.getFound())
            return new FlyToEarth4(json.getRange());
        return new EchoOpDirection5();
    }


}
