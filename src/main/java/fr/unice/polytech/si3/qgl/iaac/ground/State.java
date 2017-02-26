package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

/**
 * Created by lehmann on 11/02/17.
 */
public interface State {

    public String execute(Men men, Contracts contracts, Carte carte);

    public State wait(ReadJSON json);

}
