package fr.unice.polytech.si3.qgl.iaac.exceptions;

/**
 * Created by lehmann on 11/02/17.
 */
public class NoBudgetfield extends RuntimeException{

    public NoBudgetfield() {
        super("There is no field for budget");
    }

}
