package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;

/**
 * Created by lehmann on 04/02/17.
 */
public class AirStrategy {

    private EnumDirection lastDirection=EnumDirection.LEFT;

    public EnumDirection getLastDirection(){
        return lastDirection;
    }

    public void setLastDirection(EnumDirection lastDirection){
        this.lastDirection=lastDirection;
    }

}
