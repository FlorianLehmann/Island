package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.EXPLOIT;

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
        if (men.getRessource(((String) ReadJSON.getContracts().get(0))).getAmount() <= 0)
            men.setState(new State11());//avance
        else
            men.setState(new State4());

    }

}
