package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 11/12/2016.
 */
public class State26 implements State {

    @Override
    public void execute(Drone drone) {
        drone.setAction(LAND.toString(drone.getCaseCreek(),""));

    }

    @Override
    public void wait(Drone drone) {
        drone.setState(new State11());
    }
}
