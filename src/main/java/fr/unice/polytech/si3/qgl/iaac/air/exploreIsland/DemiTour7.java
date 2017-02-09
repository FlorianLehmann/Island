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
    private EnumDirection direction;

    public DemiTour7(int etat,EnumDirection direction){
        this.etat=etat;
        this.direction=direction;
    }
    public DemiTour7(int etat){this.etat=etat;}

    public String execute(Drone drone){
        if(etat==0) {
            if (drone.getLastDirection().equals(EnumDirection.LEFT)){
                direction=EnumDirection.LEFT;
                return drone.heading(EnumDirection.RIGHT);
            }
            else {
                direction=EnumDirection.RIGHT;
                return drone.heading(EnumDirection.LEFT);
            }
        }
        else {
            return drone.heading(direction);
        }

    }

    public State wait(ReadJSON json){
        if(etat==0){
            DemiTour7 next=new DemiTour7(1,direction);
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
