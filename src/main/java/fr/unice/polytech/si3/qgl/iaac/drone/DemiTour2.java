package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.FLY;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

public class DemiTour2 implements State {

    @Override
    public void execute(Drone drone) {

        drone.setAction(FLY.toString(""));
        drone.changeCoord(FLY, EnumDirection.getEnumDirection(drone.getDirection().front()));
    }

    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get(COST.toString()));

        drone.setState(new DemiTour3());
    }


}
