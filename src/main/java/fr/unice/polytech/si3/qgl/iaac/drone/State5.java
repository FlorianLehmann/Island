package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.HEADING;

public class State5 implements State {

    /**
     * Tourne à droite
     */
    @Override
    public void execute(Drone drone) {
        drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));

        drone.setAction(HEADING.toString(drone.getDirection().right()));
        drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
        //drone.setLastDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));//rajout
        drone.setLastDirection("R");
    }

    /**
     * Change the state
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        drone.setState(new State10());

    }


}
