package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.*;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by lehmann on 04/02/17.
 */
public class Contracts {

    private List<Contract> primaryContracts;
    private List<Contract> secondaryContracts;

    /**
     * default constructor
     */
    public Contracts() {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
    }

    /**
     *
     * @param contract
     */
    public void add(Contract contract)  {
        if (contract.getName().isPrimary())
            primaryContracts.add(contract);
        else
            secondaryContracts.add(contract);
    }

    /**
     *
     * @param resource
     */
    public void remove(EnumResources resource) {
        if (resource.isPrimary()) {
            for (int i = 0; i < primaryContracts.size(); i++)
                if (primaryContracts.get(i).getName() == resource)
                    primaryContracts.remove(i);
        }
        else{
            for (int i = 0; i < secondaryContracts.size(); i++)
                if (secondaryContracts.get(i).getName() == resource)
                    secondaryContracts.remove(i);
        }
    }

    /**
     *
     * @return true if all contracts are completed
     */
    public boolean isCompleted() {
        return primaryContracts.isEmpty();/* && secondaryContracts.isEmpty(); //todo /*&& secondaryContracts.isEmpty()*/
        // ;
    }

    public boolean isPrimaryCompleted(){
        return primaryContracts.isEmpty();
    }

    public boolean isSecondaryCompleted(){
        return secondaryContracts.isEmpty();
    }

    /**
     * Primary contracts are sorted
     */
    public void sortPrimaryContracts() {
        List<Contract> primaryContractsSorted = new ArrayList();
        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (FISH == primaryContracts.get(i).getName()) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (WOOD == primaryContracts.get(i).getName()) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (QUARTZ == primaryContracts.get(i).getName()) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
        }

        for (int i = 0; i < primaryContracts.size() ; i++)
            primaryContractsSorted.add(primaryContracts.get(i));

        primaryContracts = primaryContractsSorted;

    }

    public void sortSecondaryContracts(){
        List<Contract> secondaryContractsSorted = new ArrayList();
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (PLANK == secondaryContracts.get(i).getName()) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (LEATHER == secondaryContracts.get(i).getName()) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (GLASS == secondaryContracts.get(i).getName()) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (RUM == secondaryContracts.get(i).getName()) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (ORE == secondaryContracts.get(i).getName()) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
        }
        secondaryContracts=secondaryContractsSorted;


    }
    //todo si il n'y a rien nullpointer
    public Contract getContract() {
        return primaryContracts.get(0);
    }

    public Contract getSecondaryContract(){
        return secondaryContracts.get(0);
    }
}
