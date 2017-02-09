package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

/**
 * Created by Quentin on 05/02/2017.
 */
public class Fly implements State {

    @Override
    public String execute(Drone drone) {
        return drone.fly();
    }

    @Override
    public State wait(ReadJSON json) {
        return new EchoLeft();
    }

    @Override
    public boolean isOver() {
        return true;
    }
}
