package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.exceptions.DeptBudgetException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lehmann on 04/02/17.
 */
public class BudgetTest {

    Budget budget;

    @Before
    public void defineContext() throws DeptBudgetException {
        budget = new Budget(1500);
    }

    @Test
    public void defineBadContext() {
        try {
            new Budget(-100);
            assertTrue(false);
        }
        catch (DeptBudgetException exception)
        {
            assertTrue(true);
        }
        try {
            new Budget(0);
            assertTrue(false);
        }
        catch (DeptBudgetException exception)
        {
            assertTrue(true);
        }
        try {
            new Budget(1);
            assertTrue(true);
        }
        catch (DeptBudgetException exception)
        {
            assertTrue(false);
        }
    }

    @Test
    public void SubtractCosts() {
        assertTrue(budget.hasBudget());
        budget.subBudget(100);
        assertTrue(budget.hasBudget());
        budget.subBudget(700);
        assertFalse(budget.hasBudget());
        budget.subBudget(800);
        assertFalse(budget.hasBudget());
    }

}
