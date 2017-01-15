package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State0 implements State {

    @Override
    public void execute(Men men) {

    }

    @Override
    public void wait(Men men) {
        men.subBudget((int) ReadJSON.getInformations().get("cost"));

        men.setState(new State1());
    }

}
