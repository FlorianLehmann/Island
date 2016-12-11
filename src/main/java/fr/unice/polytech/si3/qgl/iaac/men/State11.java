package fr.unice.polytech.si3.qgl.iaac.men;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;


public class State11 implements State {

    /**
     *
     * Stop la partie
     *
     */
    @Override
    public void execute(Men men) {
	men.setAction(STOP.toString(""));//on débarque!
	//drone.setAction(LAND.toString("", drone.getACrique()));//on débarque!
    }

    /**
     *
     * Do nothing
     *
     */
    @Override
    public void wait(Men men) {

//        men.subBudget((int) ReadJSON.getInformations().get("cost"));
    }

}
