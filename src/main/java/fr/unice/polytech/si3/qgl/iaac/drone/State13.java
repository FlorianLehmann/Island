package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;

public class State13 implements State {

    /**
     * Demande un echo dans la direction du drone
     */
    @Override
    public void execute(Drone drone) {
        drone.setAction(ECHO.toString(drone.getDirection().front()));
    }

    /**
     * Analyse du résultat
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        if (ReadJSON.getInformations().get("found").equals("GROUND") || drone.getLastGround()) {
            drone.setState(new State14());
            if (!ReadJSON.getInformations().get("found").equals("GROUND"))
                drone.setLastGround(false);
            if (ReadJSON.getInformations().get("found").equals("GROUND"))
                drone.setLastGround(true);
        } else {
            drone.setState(new State15());
            drone.setCaseToTarget((int) ReadJSON.getInformations().get("range"));
        }
        if (ReadJSON.getInformations().get("found").equals("OUT_OF_RANGE") && (((int) ReadJSON.getInformations().get("range")) == 0)) {
            drone.setState(new State11());
        }
    }
}
