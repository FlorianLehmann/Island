package fr.unice.polytech.si3.qgl.iaac.json;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;

import java.util.List;

/**
 * Created by florian on 22/03/2017.
 */
public class ReadJSON2 {

    private int men;
    private int budget;
    //private List<Contract> contracts;
    private EnumOrientation heading;
    private int cost;
    private String status;
    private int range;
    private String found;
    private List<String> biomes;
    private List<String> creeks;
    private List<String> sites;
    private int altitude;
    private List<String> resources;

    private int amount;
    private int production;
    private String kind;

    public int getMen() {
        return men;
    }

    public int getBudget() {
        return budget;
    }

    /*public List<Contract> getContracts() {
        return contracts;
    }*/

    public EnumOrientation getHeading() {
        return heading;
    }

    public int getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public int getRange() {
        return range;
    }

    public String getFound() {
        return found;
    }

    public List<String> getBiomes() {
        return biomes;
    }

    public List<String> getCreeks() {
        return creeks;
    }

    public List<String> getSites() {
        return sites;
    }

    public int getAltitude() {
        return altitude;
    }

    public List<String> getResources() {
        return resources;
    }

    public int getAmount() {
        return amount;
    }

    public int getProduction() {
        return production;
    }

    public String getKind() {
        return kind;
    }





}
