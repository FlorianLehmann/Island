package sample.bot.men;

import static sample.bot.EnumJSON.STOP;


public class State11 implements State {

    /**
     * Stop la partie
     */
    @Override
    public void execute(Men men) {
        men.setAction(STOP.toString(""));//on débarque!
        //drone.setAction(LAND.toString("", drone.getACrique()));//on débarque!
    }

    /**
     * Do nothing
     */
    @Override
    public void wait(Men men) {

//        men.subBudget((int) ReadJSON.getInformations().get("cost"));
    }

}
