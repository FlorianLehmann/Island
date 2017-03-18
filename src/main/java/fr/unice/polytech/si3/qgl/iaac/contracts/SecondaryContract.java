/*package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.exceptions.NoAmountContractException;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

/**
 * Created by sebde on 27/02/2017.
 */
/*
public class SecondaryContract extends Contract{
    private boolean isFirstPrimaryCompleted=false;
    private boolean isSecondaryPrimaryCompleted=false;
    private int nbFirstRessource=0;
    private int nbSecondRessource=0;
    private int nbPrimaryRessources=0;

    public SecondaryContract(EnumResources name, int amount) throws NoAmountContractException{
        super(name, amount);
        nbPrimaryRessources=((EnumManufacturedResources)name).getNeeded().size();
    }

    public int getNbPrimaryRessources(){
        return nbPrimaryRessources;
    }

    public boolean isFirstPrimaryCompleted(){
        return isFirstPrimaryCompleted;
    }
    public boolean isSecondaryPrimaryCompleted(){
        return isSecondaryPrimaryCompleted;
    }

    public void addFirstRessource(int nb){
        nbFirstRessource+=nb;
        calculateFirstPrimary();
    }
    public void addSecondaryRessource(int nb){
        nbSecondRessource+=nb;
        calculateSecondPrimary();
    }
    public int getNbFirstRessource(){
        return nbFirstRessource;
    }
    public int getNbSecondRessource(){
        return nbSecondRessource;
    }

    private void calculateFirstPrimary(){
        if(nbFirstRessource>=(1.1*amount*((EnumManufacturedResources)name).getNbNeeded().get(0))/((EnumManufacturedResources)name).getNbCreated()){
            isFirstPrimaryCompleted=true;
        }
    }

    private void calculateSecondPrimary(){
        if(nbSecondRessource>=(1.1*amount*((EnumManufacturedResources)name).getNbNeeded().get(1))/((EnumManufacturedResources)name).getNbCreated()){
            isSecondaryPrimaryCompleted=true;
        }
    }

}*/
