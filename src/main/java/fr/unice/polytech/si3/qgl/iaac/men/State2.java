package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import java.util.Stack;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.EXPLORE;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class State2 implements State {

    /**
     *
     *
     *
     */
    @Override
    public void execute(Men men) {
        String tmp = (String) ReadJSON.getContracts().get(0);
        men.setAction(EXPLORE.toString(tmp));

    }

    /**
     *
     *
     *
     */
    @Override
    public void wait(Men men) {
        men.subBudget((int) ReadJSON.getInformations().get(COST.toString()));

        boolean resource;
        resource = false;
        for (int i = 0; i < ReadJSON.getResources().size(); i++) {
            if (ReadJSON.getResources().get(i).equals( ReadJSON.getContracts().get(0)))
                resource = true;

        }
        if (resource == true)
            men.setState(new State3());
        else
            men.setState(new State4());

    }


}
