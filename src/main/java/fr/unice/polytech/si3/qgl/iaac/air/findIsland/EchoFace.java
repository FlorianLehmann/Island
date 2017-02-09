package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.FlyToEarth4;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.FRONT;

/**
 * Created by Quentin on 04/02/2017.
 */
public class EchoFace implements State {

    @Override
    public String execute(Drone drone) {
        return drone.echo(FRONT);
    }

    @Override
    public State wait(ReadJSON json) {
        if(json.getGround())
            return new FlyToEarth4(json.getRange());

        return new EchoLeft();
    }

    @Override
    public boolean isOver() {
        return true;
    }
}
