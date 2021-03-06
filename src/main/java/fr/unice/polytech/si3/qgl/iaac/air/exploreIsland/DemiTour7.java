package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.LEFT;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.RIGHT;

/**
 * Created by sebde on 05/02/2017.
 */
public class DemiTour7 implements State {

    private int state=0;
    private EnumDirection direction;

    public DemiTour7(int etat,EnumDirection direction){
        this.state=etat;
        this.direction=direction;
    }

    public DemiTour7(int state){
        this.state=state;
    }

    public String execute(Drone drone){
        if(state==0) {
            if (LEFT.equals(drone.getLastDirection())){
                direction=RIGHT;//LEFT
                return drone.heading(RIGHT);
            }
            else {
                direction=LEFT;//RIGHT
                return drone.heading(LEFT);
            }
        }
        else {
            return drone.heading(direction);
        }

    }

    public State nextState(ReadJSON json) {
        if(state==0){
            DemiTour7 next=new DemiTour7(1,direction);
            return next;
        }
        else {
            EchoFront8 next=new EchoFront8();
            return next;
        }
    }

    /*public String execute(Drone drone){
        if(state==0) {
            if (LEFT.equals(drone.getLastDirection())){
                direction=LEFT;
                return drone.heading(RIGHT);
            }
            else {
                direction=RIGHT;
                return drone.heading(LEFT);
            }
        }
        else {
            return drone.heading(direction);
        }

    }

    public State wait(ReadJSON json){
        if(state==0){
            state = 1;
            return this;
        }
        return new EchoFront8();
    }*/


}
