package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.*;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;

/**
 * Created by sebde on 04/02/2017.
 */
public class Echo2 implements State {

    public String execute(Drone drone){
        return drone.echo(FRONT);
    }

    public State nextState(ReadJSON2 json){
        if(json.getAnswer().getFound())
            return new FlyToEarth4(json.getAnswer().getRange());
        return new EchoOpDirection5();
    }


}
