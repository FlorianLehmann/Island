package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;

/**
 * Created by lehmann on 12/02/17.
 */
public class Land implements State {

    private Carte carte;
    private int nbMen;

    public Land(int nbMen) {
        this.nbMen = nbMen;
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        this.carte = carte;
        if (nbMen > 8)
            nbMen = 8;
        return EnumJSON.LAND.toString(carte.getCreekID(), nbMen-1);
    }

    @Override
    public State changeState(ReadJSON2 json) {
        return new ReachResources(carte);
    }

}
