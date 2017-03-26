package fr.unice.polytech.si3.qgl.iaac.contracts;


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
    private int collected;
    private int required;

    /**
     * default constructor
     * @param amount
     * @param resource
     * @throws NoAmountContractException
     */
    public Contract(int amount, EnumResources resource) throws NoAmountContractException {
            this.name =  resource;
            if (amount <= 0)
                throw new NoAmountContractException();
            this.collected = 0;
            required = amount;
    }

    /**
     * reduce the collected with quantity collected
     * @param amount
     */
    public void add(int amount){
        this.collected += amount;
    }

    /**
     * increase the collected
     * @param amount
     */
    public void sub(int amount){
        this.collected -= amount;
        if (this.collected < 0)
            this.collected = 0;
    }

    /**
     *
     * @return the name of the resource
     */
    public EnumResources getName() {
        return name;
    }

    /**
     * return the collected of resource
     * @return
     */
    public int getCollected() {
        return collected;
    }

    /**
     *
     * @return true if the contract is completed
     */
    public boolean isCompleted() {
        return collected >= required;
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
