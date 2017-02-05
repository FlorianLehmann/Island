package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by sebde on 05/02/2017.
 */
public class DemiTour7 implements State {
    private int etat=0;

    public DemiTour7(int etat){
        this.etat=etat;
    }

    public String execute(Drone drone){
        AirStrategy strat=new AirStrategy();
        if(etat==0) {
            if (strat.getLastDirection().equals(EnumDirection.LEFT)) return drone.heading(EnumDirection.RIGHT);
            else {
                return drone.heading(EnumDirection.LEFT);
            }
        }
        else {
            if (strat.getLastDirection().equals(EnumDirection.LEFT)){
                strat.setLastDirection(EnumDirection.RIGHT);
                return drone.heading(EnumDirection.RIGHT);
            }
            else {
                strat.setLastDirection(EnumDirection.LEFT);
                return drone.heading(EnumDirection.LEFT);

            }
        }

    }

    public State wait(ReadJSON json){
        if(etat==0){
            DemiTour7 next=new DemiTour7(1);
            return next;
        }
        else {
            EchoFront8 next=new EchoFront8();
            return next;
        }
    }

    public boolean isOver(){
        return true;
    }
}
