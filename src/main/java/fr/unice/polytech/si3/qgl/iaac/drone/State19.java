package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class State19 implements State {


    /**
     * Demande d'un echo
     * @param drone
     */
    @Override
    public void execute(Drone drone) {

        drone.setAction(ECHO.toString(drone.getDirection().front()));
    }

    /**
     * Analyse du résultat
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        if ("GROUND".equals(ReadJSON.getInformations().get(FOUND.toString()))) {
            drone.setCaseToTarget((int) ReadJSON.getInformations().get(RANGE.toString()));
            drone.setState(new State20());
        } else {
            drone.setState(new DemiTour1());
        }
    }

}
