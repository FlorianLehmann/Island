package fr.unice.polytech.si3.qgl.iaac.exceptions;

/**
 * Created by lehmann on 11/02/17.
 */
public class NoPOIException extends RuntimeException{

    public NoPOIException(String str) {
        super("There is no POI of type : " + str);
    }
}
