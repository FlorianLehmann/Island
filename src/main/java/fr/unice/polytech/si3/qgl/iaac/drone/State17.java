package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.HEADING;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class State17 implements State {

    /**
     *
     * @param drone
     */
    @Override
    public void execute(Drone drone) {
        if (drone.getOpposeLastDirection().equals("R")) {
            drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().right()));
            drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().right()));
        } else {
            drone.changeCoord(HEADING, EnumDirection.getEnumDirection(drone.getDirection().left()));
            drone.setDirection(EnumDirection.getEnumDirection(drone.getDirection().left()));
        }
        drone.setAction(HEADING.toString(drone.getDirection().front()));


    }

    /**
     * Analyse du résultat
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        drone.setState(new State18());

    }

}
