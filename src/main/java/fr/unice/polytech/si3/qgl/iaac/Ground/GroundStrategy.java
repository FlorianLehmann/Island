package fr.unice.polytech.si3.qgl.iaac.Ground;

import fr.unice.polytech.si3.qgl.iaac.Budget;
import fr.unice.polytech.si3.qgl.iaac.Carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.Ground.State;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.STOP;

/**
 * Created by lehmann on 11/02/17.
 */
public class GroundStrategy {

    private State state;
    private int nbMen;
    private Men men;
    private ReadJSON json;
    private Carte carte;
    private Budget budget;

    /**
     * default constructor
     * @param nbMen
     * @param json
     * @param men
     * @param carte
     */
    public GroundStrategy(int nbMen, ReadJSON json, Men men, Carte carte, Budget budget){
        this.nbMen = nbMen;
        this.men = men;
        this.json = json;
        this.carte = carte;
        this.budget = budget;
        //state = new State();//todo
    }

    /**
     * take an action
     * @return
     */
    public String takeAction() {
        if (budget.hasBudget())
            return state.execute(men);
        return STOP.toString("");
    }

    /**
     * Analyse results
     */
    public void acknowledgeResults() {
        state = state.wait(json);
        carte.addGroundCase(men.getCoord());
    }

}
