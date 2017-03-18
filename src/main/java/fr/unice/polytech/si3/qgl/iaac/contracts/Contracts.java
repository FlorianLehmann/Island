package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
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
    private List<Contract> temporaryPrimaryContracts;
    private List<Contract> temporarySecondaryContracts;

    /**
     * default constructor
     */
    public Contracts() {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
        temporaryPrimaryContracts = new ArrayList<>();
        temporarySecondaryContracts = new ArrayList<>();
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
    public void sortPrimaryContracts(int budget) {
        List<Contract> primaryContractsSorted = new ArrayList();
        int amountMax = budget / 2;
        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (FISH == primaryContracts.get(i).getName() && primaryContracts.get(i).getAmount() <= amountMax ) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
            else if (FISH == primaryContracts.get(i).getName() && primaryContracts.get(i).getAmount() >= amountMax ){
                notAPriorityPrimaryContract(i);
                primaryContracts.remove(i);
            }
        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (WOOD == primaryContracts.get(i).getName() && primaryContracts.get(i).getAmount() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
            else if (WOOD == primaryContracts.get(i).getName() && primaryContracts.get(i).getAmount() >= amountMax ){
                notAPriorityPrimaryContract(i);
                primaryContracts.remove(i);
            }

        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (QUARTZ == primaryContracts.get(i).getName() && primaryContracts.get(i).getAmount() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
            else if (QUARTZ == primaryContracts.get(i).getName() && primaryContracts.get(i).getAmount() >= amountMax ){
                notAPriorityPrimaryContract(i);
                primaryContracts.remove(i);
            }


        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (primaryContracts.get(i).getAmount() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
            else {
                notAPriorityPrimaryContract(i);
                primaryContracts.remove(i);
            }

        }

        primaryContracts = primaryContractsSorted;

    }

    public void sortSecondaryContracts(int budget){
        List<Contract> secondaryContractsSorted = new ArrayList();
        int amountMax = budget / 2;
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (PLANK == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() <= amountMax) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
            else if(PLANK == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() >= amountMax){
                notAPrioritySecondaryContract(i);
                secondaryContracts.remove(i);
            }


        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (LEATHER == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() <= amountMax) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
            else if(LEATHER == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() >= amountMax){
                notAPrioritySecondaryContract(i);
                secondaryContracts.remove(i);
            }
        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (GLASS == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() <= amountMax) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
            else if(GLASS == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() >= amountMax) {
                notAPrioritySecondaryContract(i);
                secondaryContracts.remove(i);
            }
        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (RUM == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() <= amountMax) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
            else if(RUM == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() >= amountMax) {
                notAPrioritySecondaryContract(i);
                secondaryContracts.remove(i);
            }
        }
        for (int i = 0; i < secondaryContracts.size() ; i++) {
            if (ORE == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() <= amountMax) {
                secondaryContractsSorted.add(secondaryContracts.get(i));
                secondaryContracts.remove(i);
            }
            else if(ORE == secondaryContracts.get(i).getName() && secondaryContracts.get(i).getAmount() >= amountMax){
                notAPrioritySecondaryContract(i);
                secondaryContracts.remove(i);
            }
        }
        secondaryContracts=secondaryContractsSorted;
    }

    public boolean containRessource(EnumPrimaryResources ressource){
        for(Contract contract:primaryContracts){
            if(!contract.isCompleted() && contract.getName().equals(ressource))return true;
        }
        return false;
    }

    public void verifyContractCompleted(Contract contract){
        if(contract.isCompleted()){
            remove(contract.getName());
        }
    }




    //todo si il n'y a rien nullpointer
    public Contract getPrimaryContract() {
        return primaryContracts.get(0);
    }

    public Contract getSecondaryContract(){
        return secondaryContracts.get(0);
    }

    public Contract getPrimaryContract(EnumPrimaryResources ressource){
        for(Contract contract:primaryContracts){
            if(contract.getName().equals(ressource))return contract;
        }
        return null;
    }

    public void switchToRestOfContratc(){
        primaryContracts=temporaryPrimaryContracts;
        secondaryContracts=temporarySecondaryContracts;
    }

    private void notAPriorityPrimaryContract(int ressource){
        temporaryPrimaryContracts.add(primaryContracts.get(ressource));
    }
    private void notAPrioritySecondaryContract(int ressource){
        temporarySecondaryContracts.add(secondaryContracts.get(ressource));
    }
}
