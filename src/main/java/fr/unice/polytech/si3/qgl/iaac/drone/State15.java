package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class State15 implements State {


    /**
     * Demande un echo
     * @param drone
     */
    @Override
    public void execute(Drone drone) {
        if (drone.getOpposeLastDirection().equals("R"))
            drone.setAction(ECHO.toString(drone.getDirection().right()));
        else
            drone.setAction(ECHO.toString(drone.getDirection().left()));

    }

    /**
     * Analyse du résultat
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        if ("GROUND".equals(ReadJSON.getInformations().get(FOUND.toString())) && (((int) ReadJSON.getInformations().get(RANGE.toString())) < 4)) {
            drone.setState(new State16());
        } else {
            drone.setState(new State17());
        }
        if (drone.getCaseToTarget() == 0) {
            drone.setState(new Stop());
        }
    }


}
