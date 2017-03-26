package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

/**
 * Created by sebde on 05/02/2017.
 */
public class EchoFront8 implements State {


    public String execute(Drone drone){
        return drone.echo(EnumDirection.FRONT);
    }

    public State nextState(ReadJSON json){
        if(json.getFound())
            return new FlyToEarth4(json.getRange());
        return new TourComplet(0);
    }

}
