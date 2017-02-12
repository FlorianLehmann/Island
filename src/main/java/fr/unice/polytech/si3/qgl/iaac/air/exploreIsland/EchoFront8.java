package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 05/02/2017.
 */
public class EchoFront8 implements State {

    public String execute(Drone drone){
        return drone.echo(EnumDirection.FRONT);
    }

    public State wait(ReadJSON json){
        if(json.getGround())
            return new FlyToEarth4(json.getRange());
        return new TourComplet1();
    }

    public boolean isOver(){
        return true;
    }
}
