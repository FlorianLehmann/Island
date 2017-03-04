package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.exceptions.NoAmountContractException;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

/**
 * Created by sebde on 27/02/2017.
 */
public class PrimaryContract extends Contract {


    public PrimaryContract(EnumResources name, int amount) throws NoAmountContractException {
        super(name,amount);
    }
}
