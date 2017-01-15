package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class State13 implements State {


    /**
     * Demande un echo dans la direction du drone
     * @param drone
     */
    @Override
    public void execute(Drone drone) {
        drone.setAction(ECHO.toString(drone.getDirection().front()));
    }


    /**
     *
     * Analyse du résultat
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        if ("GROUND".equals(ReadJSON.getInformations().get(FOUND.toString())) || drone.getLastGround()) {
            drone.setState(new State14());
            if (!"GROUND".equals(ReadJSON.getInformations().get(FOUND.toString())))
                drone.setLastGround(false);
            if ("GROUND".equals(ReadJSON.getInformations().get(FOUND.toString())))
                drone.setLastGround(true);
        } else {
            drone.setState(new State15());
            drone.setCaseToTarget((int) ReadJSON.getInformations().get(RANGE.toString()));
        }
        if ("OUT_OF_RANGE".equals(ReadJSON.getInformations().get(FOUND.toString())) && (((int) ReadJSON.getInformations().get(RANGE.toString())) == 0)) {
            drone.setState(new Stop());
        }
    }
}
