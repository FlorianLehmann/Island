package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
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
        if (contract.getName().isPrimary()) {
            primaryContracts.add(contract);
        }
        else {
            secondaryContracts.add(contract);
        }
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
        throw new RuntimeException("There are not primary contracts");
    }

    public void addColectedContract(int collect, EnumPrimaryResources resource) {
        for(Contract contract:primaryContracts){
            if(contract.getName() == resource)
                contract.sub(collect);
        }
    }

    public void allocateContracts() {
        for (Contract secondaryContract: secondaryContracts)
            for (Contract primaryContract: primaryContracts)
                if (((EnumManufacturedResources)secondaryContract.getName()).getNeeded())
    }

    public boolean couldCompleteAnotherContract() {
        throw new UnsupportedOperationException();
    }
}

//on ajoute les ressources nescessaire aux contrats secondaire dans les contrats primaires
//regarde si on a assez de budget pour fabriquer les contrats secondaires
//on regarde