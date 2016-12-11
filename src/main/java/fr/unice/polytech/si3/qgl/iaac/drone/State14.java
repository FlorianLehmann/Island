package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;

public class State14 implements State {

    /**
     *
     * Le drone avance d'une case
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(FLY.toString(""));
    drone.changeCoord(FLY, drone.getDirection());
    }

    /**
     *
     * Retour a l'etat initial
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	drone.setState(new State12());
    }
}
