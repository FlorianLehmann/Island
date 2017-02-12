package fr.unice.polytech.si3.qgl.iaac.Ground;

import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.Contract;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

/**
 * Created by lehmann on 12/02/17.
 */
public class Explore implements State {

    private Contract contract;

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        contract =  contracts.getContract();
        return men.explore();
    }


    @Override
    public State wait(ReadJSON json) {
        boolean resource;
        resource = false;
        for (int i = 0; i < json.getResources().size() ; i++) {
            if(json.getResources().get(i).equals(contract.getName().toString()))
                resource = true;
        }

        if (resource)
            return new Exploit();
        else
            return new TounerRond();
    }
}
