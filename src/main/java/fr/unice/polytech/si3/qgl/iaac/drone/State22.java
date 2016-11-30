package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State22 implements State {

    @Override
    public void execute(Drone drone) {

	drone.setAction(FLY.toString(""));
    }

    @Override
    public void wait(Drone drone) {
	drone.setState(new State23());
    }
	

}
