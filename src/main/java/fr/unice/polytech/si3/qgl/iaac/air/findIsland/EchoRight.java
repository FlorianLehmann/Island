package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.RIGHT;

/**
 * Created by Quentin on 05/02/2017.
 */
public class EchoRight implements State {
    @Override
    public String execute(Drone drone) {
        return drone.echo(RIGHT);
    }

    @Override
    public State nextState(ReadJSON2 json) {
        if(json.getAnswer().getFound())
            return new HeadingRight();
        return new Fly();
    }

}
