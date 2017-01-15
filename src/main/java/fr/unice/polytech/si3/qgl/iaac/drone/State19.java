package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;

public class State19 implements State {

    /**
     * Demand un echo
     */
    @Override
    public void execute(Drone drone) {

        drone.setAction(ECHO.toString(drone.getDirection().front()));
    }

    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        if (ReadJSON.getInformations().get("found").equals("GROUND")) {
            drone.setCaseToTarget((int) ReadJSON.getInformations().get("range"));
            drone.setState(new State20());
        } else {
            drone.setState(new State21());
        }
    }

}
