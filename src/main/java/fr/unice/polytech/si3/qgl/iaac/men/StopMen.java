package fr.unice.polytech.si3.qgl.iaac.men;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.STOP;


public class StopMen implements State {

    /**
     * Stop la partie
     */
    @Override
    public void execute(Men men) {
        men.setAction(STOP.toString(""));
    }


    /**
     * Do nothing
     * @param men
     */
    @Override
    public void wait(Men men) {

    }

}
