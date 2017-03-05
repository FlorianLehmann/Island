package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.Budget;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

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
    }

    /**
     * take an action
     * @return
     */
    public String takeAction() {
        if (budget.hasBudget()) {
            contracts.sortPrimaryContracts(budget.getBudget());
            contracts.sortSecondaryContracts(budget.getBudget());
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
        //todo
        //carte.addGroundCase(men.getCoord());
    }

}
