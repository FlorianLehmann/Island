package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.exceptions.DeptBudgetException;

/**
 * Created by lehmann on 04/02/17.
 */
public class Budget {

    private int budget;

    /**
     * dafault constructor
     * @param budget
     * @throws DeptBudgetException
     */
    public Budget(int budget) throws DeptBudgetException {
        if (budget <= 0)
            throw new DeptBudgetException();
        this.budget = budget;
    }

    /**
     * subtract a cost to a budget
     * @param cost
     */
    public void subBudget(int cost) {
        budget -= cost;
    }

    /**
     *
     * @return true if we have enough budget and false otherwise
     */
    public boolean hasBudget() {
        return budget>700;
    }
}
