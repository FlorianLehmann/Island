package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.exceptions.NoAmountContractException;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

/**
 * Created by sebde on 27/02/2017.
 */
public class SecondaryContract extends Contract{
    protected boolean isFirstPrimaryCompleted=false;
    protected int nbFirstRessource=0;
    protected int nbSecondRessource=0;

    public SecondaryContract(EnumResources name, int amount) throws NoAmountContractException{
        super(name, amount);
    }

    public boolean isFirstPrimaryCompleted(){
        return isFirstPrimaryCompleted;
    }

}
