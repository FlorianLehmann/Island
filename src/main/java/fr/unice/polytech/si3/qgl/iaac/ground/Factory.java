package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.STOP;

/**
 * Created by florian on 18/03/2017.
 */
public class Factory implements State {


    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        throw new UnsupportedOperationException();
        /*if (!contracts.couldCompleteAnotherContract())
            return STOP.toString("");*/

    }

    @Override
    public State wait(ReadJSON json) {
        return this;
    }
}
