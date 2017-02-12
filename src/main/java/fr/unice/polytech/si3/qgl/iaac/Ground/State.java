package fr.unice.polytech.si3.qgl.iaac.Ground;

import fr.unice.polytech.si3.qgl.iaac.Carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

/**
 * Created by lehmann on 11/02/17.
 */
public interface State {

    public String execute(Men men, Contracts contracts, Carte carte);

    public State wait(ReadJSON json);

}
