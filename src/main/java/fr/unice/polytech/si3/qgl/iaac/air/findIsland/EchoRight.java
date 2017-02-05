package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.RIGHT;

/**
 * Created by Quentin on 05/02/2017.
 */
public class EchoRight implements State {
    @Override
    public String execute(Drone drone) {
        return drone.echo(RIGHT);
    }

    @Override
    public State wait(ReadJSON json) {
        if(json.getGround()){
            HeadingRight next = new HeadingRight();
            return next;
        }

        Fly next = new Fly();
        return next;

    }

    @Override
    public boolean isOver() {
        return true;
    }
}
