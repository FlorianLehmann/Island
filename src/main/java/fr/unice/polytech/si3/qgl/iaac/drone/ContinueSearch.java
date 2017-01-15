package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.HEADING;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

public class ContinueSearch implements State {

    /**
     * Demande de changement de cap
     */
    @Override
    public void execute(Drone drone) {

        if (drone.getNbCaseLeft() > drone.getNbCaseRight()) {
            drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));

            drone.setAction(HEADING.toString(drone.getDirection().left()));
            drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
        } else {
            drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));

            drone.setAction(HEADING.toString(drone.getDirection().right()));
            drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
        }
    }

    /**
     * Set the state
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        drone.setState(new EchoFront());
    }


}
