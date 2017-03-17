package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.LEFT;

/**
 * Created by Quentin on 05/02/2017.
 */
public class HeadingLeft implements State {

    @Override
    public String execute(Drone drone) {
        return drone.heading(LEFT);
    }

    @Override
    public State wait(ReadJSON json) {
        return new EchoFace();
    }

}
