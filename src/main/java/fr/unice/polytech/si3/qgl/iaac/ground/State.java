package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

/**
 * Created by lehmann on 11/02/17.
 */
public interface State {

    public String execute(Men men, Contracts contracts, Carte carte);

    public State changeState(ReadJSON json);

}
