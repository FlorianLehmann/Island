package fr.unice.polytech.si3.qgl.iaac.contracts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoAmountContractException;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

/**
 * Created by lehmann on 04/02/17.
 */
public class Contract {

    /**
     * attributes
     */
    private EnumResources name;
    private int debt;
    private int required;

    /**
     * default constructor
     * @param amount
     * @param resource
     * @throws NoAmountContractException
     */
    @JsonCreator
    public Contract(@JsonProperty("amount") int amount, @JsonProperty("resource") EnumResources resource) throws NoAmountContractException {
            this.name =  resource;
            if (amount <= 0)
                throw new NoAmountContractException();
            this.debt = 0;
            required = amount;
    }

    /**
     * reduce the debt with quantity collected
     * @param amount
     */
    public void add(int amount){
        this.debt += amount;
    }

    /**
     * increase the debt
     * @param amount
     */
    public void sub(int amount){
        this.debt -= amount;
        if (this.debt < 0)
            this.debt = 0;
    }

    /**
     *
     * @return the name of the resource
     */
    public EnumResources getName() {
        return name;
    }

    /**
     * return the debt of resource
     * @return
     */
    public int getDebt() {
        return debt;
    }

    /**
     *
     * @return true if the contract is completed
     */
    public boolean isCompleted() {
        return debt >= required;
    }

    /**
     *
     * @return the total amount of resource required to complete this contract
     */
    public int getRequired() {
        return required;
    }

    /**
     * increase the total amount of resource required to complete this contract
     * @param amount
     */
    public void addRequired(int amount) {
        required += amount;
    }
}
