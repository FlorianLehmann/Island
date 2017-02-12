package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.FLY;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.HEADING;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

public class ContinueSearch implements State {

    /**
     * Demande de changement de cap
     * @param drone
     */
    @Override
    public void execute(Drone drone) {

        drone.changeCoord(FLY, EnumDirection.getEnumDirection(drone.getDirection().front()));

        drone.setAction(FLY.toString(drone.getDirection().front()));

    }

    /**
     * Change the state
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        drone.setState(new EchoLeft());
    }


}
