package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.FLY;

public class State22 implements State {

    @Override
    public void execute(Drone drone) {

        drone.setAction(FLY.toString(""));
        drone.changeCoord(FLY, EnumDirection.getEnumDirection(drone.getDirection().front()));
    }

    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));

        drone.setState(new State23());
    }


}
