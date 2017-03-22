package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

/**
 * Created by Quentin on 05/02/2017.
 */
public class Fly implements State {

    @Override
    public String execute(Drone drone) {
        return drone.fly();
    }

    @Override
    public State nextState(ReadJSON json) {
        return new EchoLeft();
    }

}
