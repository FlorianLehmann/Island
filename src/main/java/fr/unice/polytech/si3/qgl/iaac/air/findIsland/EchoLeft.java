package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.LEFT;

/**
 * Created by Quentin on 05/02/2017.
 */
public class EchoLeft implements State {

    @Override
    public String execute(Drone drone) {
        return drone.echo(LEFT);
    }

    @Override
    public State nextState(ReadJSON json) {
        if(json.getFound())
            return new HeadingLeft();

        return new EchoRight();
    }

}
