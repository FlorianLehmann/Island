package fr.unice.polytech.si3.qgl.iaac.men;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.STOP;


public class State11 implements State {

    /**
     * Stop la partie
     */
    @Override
    public void execute(Men men) {
        men.setAction(STOP.toString(""));
    }

    /**
     * Do nothing
     */
    @Override
    public void wait(Men men) {

    }

}
