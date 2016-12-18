package sample.bot.men;

import sample.bot.ReadJSON;

public class State0 implements State{

    @Override
    public void execute(Men men) {

    }

    @Override
    public void wait(Men men) {
        men.subBudget((int) ReadJSON.getInformations().get("cost"));

        men.setState(new State1());
    }
    
}
