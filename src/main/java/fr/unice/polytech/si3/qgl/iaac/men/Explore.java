package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.EXPLORE;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class Explore implements State {

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
            men.setState(new Exploit());
        else
            men.setState(new TournerRond());

    }


}
