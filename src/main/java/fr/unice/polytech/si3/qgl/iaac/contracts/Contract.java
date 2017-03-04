package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoAmountContractException;

/**
 * Created by lehmann on 04/02/17.
 */
public abstract class Contract {

    protected EnumResources name;
    protected int amount;

    /**
     * default constructor
     * @param name
     * @param amount
     * @throws NoAmountContractException
     */

    public Contract(){}

    public Contract(EnumResources name, int amount) throws NoAmountContractException {
            this.name =  name;
            if (amount <= 0)
                throw new NoAmountContractException();
            this.amount = amount;
    }

    /**
     *
     * @param amount
     */
    public void add(int amount){
        this.amount += amount;
    }

    /**
     *
     * @param amount
     */
    public void sub(int amount){
        this.amount -= amount;
        if (this.amount < 0)
            this.amount = 0;
    }

    /**
     *
     * @return the name of the resource
     */
    public EnumResources getName() {
        return name;
    }

    /**
     * return the amount of resource
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @return true if the contract is completed
     */
    public boolean isCompleted() {
        return amount == 0;
    }

}
