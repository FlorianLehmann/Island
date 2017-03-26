package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;

/**
 * Created by lehmann on 11/02/17.
 */
public interface State {

    public String execute(Men men, Contracts contracts, Carte carte, Budget budget);

    public State changeState(ReadJSON json);

}
