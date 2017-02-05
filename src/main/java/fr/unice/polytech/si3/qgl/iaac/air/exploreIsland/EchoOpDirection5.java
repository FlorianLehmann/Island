package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 04/02/2017.
 */
public class EchoOpDirection5 implements State {
    public String execute(Drone drone){
        AirStrategy strat=new AirStrategy();
        if(strat.getLastDirection().equals(EnumDirection.LEFT))return drone.echo(EnumDirection.RIGHT);
        else{return drone.echo(EnumDirection.LEFT);}
    }

    public State wait(ReadJSON json){
        if(json.getGround() && json.getRange()<3){
            Fly6 next=new Fly6();
            return next;
        }
        else{
            DemiTour7 next=new DemiTour7(0);
            return next;
        }

    }

    public boolean isOver(){
        return true;
    }
}
