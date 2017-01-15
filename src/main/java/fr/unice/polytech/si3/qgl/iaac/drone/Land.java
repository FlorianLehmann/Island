package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.LAND;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

/**
 * Created by sebde on 11/12/2016.
 */
public class Land implements State {

    @Override
    public void execute(Drone drone) {
        int nb = drone.getNbMen() - 1;
        if (nb > 6) {
            nb = 6;
        }
        drone.setAction(LAND.toString(drone.getACrique(), nb));
    }

    @Override
    public void wait(Drone drone) {

        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        drone.setCoord();
        drone.setEnd();
    }
}
