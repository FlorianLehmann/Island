package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.EXPLOIT;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

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
        men.subBudget((int) ReadJSON.getInformations().get(COST.toString()));

        men.getRessource((String) ReadJSON.getContracts().get(0)).setAmount(men.getRessource((String) ReadJSON.getContracts().get(0)).getAmount() - ReadJSON.getCollect());
        if (men.getRessource(((String) ReadJSON.getContracts().get(0))).getAmount() <= 0) {
            ReadJSON.getContracts().remove(0);
            ReadJSON.getAmount().remove(0);
            State1.setWayDefine(false);
            State4.init();
            men.setState(new State1());
        } else {
            men.setState(new State4());
        }

        if (ReadJSON.getContracts().size() == 0)
            men.setState(new State11());


    }

}
