package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.STOP;

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
    private Contracts contracts;


    /**
     * default constructor
     * @param nbMen
     * @param json
     * @param men
     * @param carte
     */
    public GroundStrategy(int nbMen, ReadJSON json, Men men, Carte carte, Budget budget, Contracts contracts){
        this.nbMen = nbMen;
        this.men = men;
        this.json = json;
        this.carte = carte;
        this.budget = budget;
        this.contracts = contracts;
        state = new Land(nbMen);
        contracts.allocateContracts();
    }

    /**
     * take an action
     * @return
     */
    public String takeAction() {
        if (budget.getBudget() > 700 && budget.getBudget() < 1700 ) {
            //todo manufactured ressource
            return state.execute(men, contracts, carte);
        }
        if (budget.hasBudget()) {
            return state.execute(men, contracts, carte);
        }
        return STOP.toString("");
    }

    /**
     * Analyse results
     */
    public void acknowledgeResults() {
        if (budget.hasBudget()) {
            budget.subBudget(json.getCost());
            state = state.wait(json);
        }
    }

}
