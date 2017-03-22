package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.FlyToEarth4;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.FRONT;

/**
 * Created by Quentin on 04/02/2017.
 */
public class EchoFace implements State {

    @Override
    public String execute(Drone drone) {
        return drone.echo(FRONT);
    }

    @Override
    public State nextState(ReadJSON json) {
        if(json.getAnswer().getFound())
            return new FlyToEarth4(json.getAnswer().getRange());

        return new EchoLeft();
    }


}
