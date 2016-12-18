package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.ReadJSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebde on 11/12/2016.
 */
public class State26 implements State {

    @Override
    public void execute(Drone drone) {
        int nb = drone.getNbMen()-1;
        if (nb > 6) {
            nb = 6;
        }
        drone.setAction(LAND.toString(drone.getACrique(),nb));

    }

    @Override
    public void wait(Drone drone) {

        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        drone.setCoord();
        drone.setEnd();
    }
}
