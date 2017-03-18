package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.STOP;

/**
 * Created by florian on 18/03/2017.
 */
public class Factory implements State {


    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {

        if (!contracts.couldCompleteAnotherContract())
            return STOP.toString("");

    }

    @Override
    public State wait(ReadJSON json) {
        return this;
    }
}
