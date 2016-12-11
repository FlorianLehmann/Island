package sample.bot.drone;

import static sample.bot.EnumJSON.*;
import sample.bot.EnumDirection;
import sample.bot.ReadJSON;
import java.util.HashMap;

public class State12 implements State {

    /**
     *
     * Demande un SCAN
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(SCAN.toString(""));
    drone.addCase();
    }

    /**
     *
     * Analyse du résultat
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	if(ReadJSON.getInformations().containsKey("sites") /*&& !((String) ReadJSON.getInformations().get("sites")).equals("")*/) {// TODO:
        drone.setPU((String) ReadJSON.getInformations().get("sites"));
    }
	if(ReadJSON.getInformations().containsKey("creeks") /*&& !((String)ReadJSON.getInformations().get("sites")).equals("")*/) {// TODO:
       drone.setCreek((String) ReadJSON.getInformations().get("creeks"));
    }
	if ( drone.hasPU() && (drone.getNbCreek() == 8) ) {
	    drone.setState(new State26());
	}
	else {
	    drone.setState(new State13());
	}
    }

}

