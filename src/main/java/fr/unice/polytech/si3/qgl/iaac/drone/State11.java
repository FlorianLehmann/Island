package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.STOP;


public class State11 implements State {

    /**
     * Stop la partie
     */
    @Override
    public void execute(Drone drone) {
        drone.setAction(STOP.toString(""));//on débarque!
        //drone.setAction(LAND.toString("", drone.getACrique()));//on débarque!
    }

    /**
     * Do nothing
     */
    @Override
    public void wait(Drone drone) {

        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
    }

}
