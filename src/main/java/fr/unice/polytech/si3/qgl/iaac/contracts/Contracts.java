package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import fr.unice.polytech.si3.qgl.iaac.resources.Ingredient;
import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by lehmann on 04/02/17.
 */
public class Contracts {

    private List<Contract> primaryContracts;
    private List<Contract> secondaryContracts;
    private List<Contract> notAPriorityPrimaryContract;
    private List<Contract> notAPrioritySecondaryContract;

    /**
     * default constructor
     */
    public Contracts() {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
        notAPriorityPrimaryContract = new ArrayList<>();
        notAPrioritySecondaryContract = new ArrayList<>();
    }

    /**
     * @param contract
     */
    public void add(Contract contract) {
        if (contract.getName().isPrimary()) {
            primaryContracts.add(contract);
        } else {
            secondaryContracts.add(contract);
        }
    }

    /**
     * @param resource
     */
    public void remove(EnumResources resource) {
        if (resource.isPrimary()) {
            for (int i = 0; i < primaryContracts.size(); i++)
                if (primaryContracts.get(i).getName() == resource)
                    primaryContracts.remove(i);
        } else {
            for (int i = 0; i < secondaryContracts.size(); i++)
                if (secondaryContracts.get(i).getName() == resource)
                    secondaryContracts.remove(i);
        }
    }

    /**
     * @return true if all contracts are completed
     */
    public boolean isCompleted() {
        return isPrimaryCompleted() && isSecondaryCompleted();
    }

    public boolean isPrimaryCompleted() {
        for (Contract contract: primaryContracts) {
            if(!contract.isCompleted()){
                return false;
            }
        }
        if(!notAPriorityPrimaryContract()){
            primaryContracts = changeNotAPrimaryContractToPriorityPrimaryContract(primaryContracts);
            return false;
        }
        return true;
    }

    public boolean notAPriorityPrimaryContract() {
        for (Contract contract: notAPriorityPrimaryContract) {
            if(!contract.isCompleted()){
                return false;
            }
        }
        return true;
    }

    public boolean isSecondaryCompleted() {
        return secondaryContracts.isEmpty();
    }

    public List<Contract> changeNotAPrimaryContractToPriorityPrimaryContract(List<Contract> primaryContracts){
        for (Contract contract: notAPriorityPrimaryContract){
            if(!contract.isCompleted()) {
                primaryContracts.add(contract);
            }
        }
        return primaryContracts;
    }

    public boolean containRessource(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (!contract.isCompleted() && contract.getName().equals(ressource)) {
                return true;
            }
        }
        return false;
    }

    public boolean contain(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (contract.getName().equals(ressource)) {
                return true;
            }
        }
        return false;
    }


    public void verifyContractCompleted(Contract contract) {
        if (contract.isCompleted()) {
            remove(contract.getName());
        }
    }

    public Contract getPrimaryContract() {
        return primaryContracts.get(0);
    }

    public Contract getSecondaryContract() {
        return secondaryContracts.get(0);
    }

    public Contract getPrimaryContract(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (contract.getName().equals(ressource)) return contract;
        }
        throw new RuntimeException("There are not primary contracts");
    }

    public void addColectedContract(int collect, EnumPrimaryResources resource) {
        for (Contract contract : primaryContracts) {
            if (contract.getName() == resource)
                contract.add(collect);
        }
    }

    public void allocateContracts() {
        for (Contract secondaryContract : secondaryContracts) {
            EnumManufacturedResources manufacturedResource = (EnumManufacturedResources) secondaryContract.getName();
            for (Ingredient ingredient : manufacturedResource.getIngredients()) {
                primaryContractsAdd(ingredient.getIngredient(), ingredient.getAmount() * (secondaryContract.getNeed() + ((int) secondaryContract.getNeed()/10)));
            }
        }
    }

    private void primaryContractsAdd(EnumPrimaryResources ingredient, int amount) {
        for (Contract contract : primaryContracts) {
            if (contract.getName() == ingredient) {
                contract.addNeed(amount);
                return;
            }
        }

        add(new Contract(ingredient, amount));

    }

    public boolean couldCompleteAnotherContract() {
        for (Contract secondaryContract : secondaryContracts) {
            if (!secondaryContract.isCompleted()) {
                int amount = secondaryContract.getNeed();
                List<Ingredient> ingredients = ((EnumManufacturedResources) secondaryContract.getName()).getIngredients();
                if (hasEnoughToMakeManufacturedContract(ingredients, amount))
                    return true;
            }
        }
        return false;
    }

    private boolean hasEnoughToMakeManufacturedContract(List<Ingredient> ingredients, int amount) {
        int numberOfIngredients = ingredients.size();
        for (Ingredient ingredient :
                ingredients) {
            int necessaryAmount = ingredient.getAmount()*(amount + ((int) amount/10));
            for (int i = 0; i < primaryContracts.size() ; i++) {
                if (primaryContracts.get(i).getName() == ingredient.getIngredient() && primaryContracts.get(i).getAmount() >= necessaryAmount ) {
                    numberOfIngredients--;
                }
            }
        }
        return numberOfIngredients == 0;
    }


    public Contract getManufacturedContract() {
        for (Contract secondaryContract : secondaryContracts) {
            if (!secondaryContract.isCompleted()) {
                int amount = secondaryContract.getNeed();
                List<Ingredient> ingredients = ((EnumManufacturedResources) secondaryContract.getName()).getIngredients();
                if (hasEnoughToMakeManufacturedContract2(ingredients, amount)) {
                    Contract secContract = secondaryContract;
                    secondaryContracts.remove(secondaryContract);
                    return secContract;
                }
            }
        }
        throw new RuntimeException("no Manufactured contract");
    }

    private boolean hasEnoughToMakeManufacturedContract2(List<Ingredient> ingredients, int amount) {
        int numberOfIngredients = ingredients.size();
        for (Ingredient ingredient :
                ingredients) {
            int necessaryAmount = ingredient.getAmount()*(amount + ((int) amount/10));
            for (int i = 0; i < primaryContracts.size() ; i++) {
                if (primaryContracts.get(i).getName() == ingredient.getIngredient() && primaryContracts.get(i).getAmount() >= necessaryAmount ) {
                    primaryContracts.get(i).sub(necessaryAmount);
                    numberOfIngredients--;
                }
            }
        }
        return numberOfIngredients == 0;
    }



    public void sortPrimaryContracts(int budget) {
        List<Contract> primaryContractsSorted = new ArrayList();
        int amountMax = budget / 2;

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (FISH == primaryContracts.get(i).getName() && primaryContracts.get(i).getNeed() <= amountMax ) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
            else if (FISH == primaryContracts.get(i).getName() && primaryContracts.get(i).getNeed() >= amountMax ){
                notAPriorityPrimaryContract(i);
                primaryContracts.remove(i);
            }
        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (WOOD == primaryContracts.get(i).getName() && primaryContracts.get(i).getNeed() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
            else if (WOOD == primaryContracts.get(i).getName() && primaryContracts.get(i).getNeed() >= amountMax ){
                notAPriorityPrimaryContract(i);
                primaryContracts.remove(i);
            }

        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (QUARTZ == primaryContracts.get(i).getName() && primaryContracts.get(i).getNeed() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
            else if (QUARTZ == primaryContracts.get(i).getName() && primaryContracts.get(i).getNeed() >= amountMax ){
                notAPriorityPrimaryContract(i);
                primaryContracts.remove(i);
            }


        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (primaryContracts.get(i).getNeed() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
            }
            else {
                notAPriorityPrimaryContract(i);
            }

        }

        primaryContracts = primaryContractsSorted;

    }

    private void notAPriorityPrimaryContract(int i){
        notAPriorityPrimaryContract.add(primaryContracts.get(i));
    }

    private void notAPrioritySecondaryContract(int i){
        notAPrioritySecondaryContract.add(secondaryContracts.get(i));
    }

    public Contract getContract(){
        for (Contract contract: primaryContracts) {
            if(!contract.isCompleted()){
                return contract;
            }
        }
        throw new RuntimeException("no contract");
    }

    public void changePrimaryContractToNotAPriorityPrimaryContract(Contract contract){
        for(int i=0; i< primaryContracts.size();i++){
            if(primaryContracts.get(i).getName() == contract.getName()){
                notAPriorityPrimaryContract.add(contract);
                primaryContracts.remove(i);

            }
        }
    }

}
