package fr.unice.polytech.si3.qgl.iaac.exceptions;

/**
 * Created by lehmann on 11/02/17.
 */
public class NoHeadingField extends RuntimeException {

    public NoHeadingField() {
        super("There is no field for initial heading");
    }

}
