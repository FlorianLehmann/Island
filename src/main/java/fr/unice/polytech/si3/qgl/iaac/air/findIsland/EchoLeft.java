package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.LEFT;

/**
 * Created by Quentin on 05/02/2017.
 */
public class EchoLeft implements State {

    @Override
    public String execute(Drone drone) {
        return drone.echo(LEFT);
    }

    @Override
    public State wait(ReadJSON json) {
        if(json.getGround()){
            HeadingLeft next = new HeadingLeft();
            return next;
        }

        EchoRight next = new EchoRight();
        return next;

    }

    @Override
    public boolean isOver() {
        return true;
    }
}
