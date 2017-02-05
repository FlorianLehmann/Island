package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 04/02/2017.
 */
public class Echo2 implements State {

    public String execute(Drone drone){
        return drone.echo(EnumDirection.FRONT);
    }

    public State wait(ReadJSON json){
        if(json.getGround()){
            FlyToEarth4 next=new FlyToEarth4(json.getRange());
            return next;
        }
        else{
            EchoOpDirection5 next=new EchoOpDirection5();
            return next;
        }
    }

    public boolean isOver(){
        return true;
    }

}
