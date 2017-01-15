package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class EchoRight implements State {


    /**
     * Demande un echo sur la droite
     * @param drone
     */
    @Override
    public void execute(Drone drone) {
        drone.setAction(ECHO.toString(drone.getDirection().right()));
    }

    /**
     * Analyse du résultat
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        if ("GROUND".equals(ReadJSON.getInformations().get(FOUND.toString()))) {
            drone.setState(new State5());
            drone.setCaseToTarget((int) ReadJSON.getInformations().get(RANGE.toString()));
        } else {
            drone.setState(new ContinueSearch());
            drone.setNbCaseRight((int) ReadJSON.getInformations().get(RANGE.toString()));
        }
    }


}
