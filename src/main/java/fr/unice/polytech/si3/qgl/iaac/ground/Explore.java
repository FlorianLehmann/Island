package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.getEnumPrimaryResources;

/**
 * Created by lehmann on 12/02/17.
 */
public class Explore implements State {

    private Contract contract;
    private Contracts contracts;

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        contract =  contracts.getPrimaryContract();
        this.contracts = contracts;
        return men.explore();
    }

    //TODO ne gère que la ressource en cours
    @Override
    public State wait(ReadJSON json) {
        boolean resource;
        List<EnumResources> resources = new ArrayList<>();
        resource = false;
        for (int i = 0; i < json.getResources().size() ; i++) {
            if(json.getResources().get(i).equals(contract.getName().toString()))
                resource = true;
            //TODO retourner des primary de JSON
            if(contracts.containRessource((EnumPrimaryResources)(getEnumPrimaryResources(json.getResources().get(i))))){
                resources.add(getEnumPrimaryResources(json.getResources().get(i)));
            }
        }

        if (resource)
            return new Exploit(resources);
        else
            return new DefineWay();
            //return new TounerRond();
    }
}
