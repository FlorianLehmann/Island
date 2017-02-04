package fr.unice.polytech.si3.qgl.iaac.exceptions;

/**
 * Created by lehmann on 04/02/17.
 */
public class NoAmountContractException extends RuntimeException {

    public NoAmountContractException() {
        super("The initial amount cannot be null or negative");
    }

}
