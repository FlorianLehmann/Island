package sample.bot.men;

import sample.bot.ReadJSON;

import static sample.bot.EnumJSON.EXPLOIT;

/**
 * Created by dev on 17/12/2016.
 */
public class State3 implements State {

    /**
     *
     *
     *
     */
    @Override
    public void execute(Men men) {
        String tmp = (String) ReadJSON.getContracts().get(0);
        men.setAction(EXPLOIT.toString(tmp));

    }

    /**
     *
     *
     *
     */
    @Override
    public void wait(Men men) {
        men.getRessource((String) ReadJSON.getContracts().get(0)).setAmount(men.getRessource((String) ReadJSON.getContracts().get(0)).getAmount() - ReadJSON.getCollect());
        if (men.getRessource(((String) ReadJSON.getContracts().get(0))).getAmount() <= 0)
            men.setState(new State11());//avance
        else
            men.setState(new State4());

    }

}
