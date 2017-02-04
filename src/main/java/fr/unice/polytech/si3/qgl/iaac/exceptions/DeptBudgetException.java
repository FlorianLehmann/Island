package fr.unice.polytech.si3.qgl.iaac.exceptions;

/**
 * Created by lehmann on 04/02/17.
 */
public class DeptBudgetException extends Exception {

    public DeptBudgetException() {
        System.out.println("The initial budget cannot be negative");
    }
}
