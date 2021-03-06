/*package fr.unice.polytech.si3.qgl.iaac.json;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.util.ArrayList;
import java.util.List;


public class Answer {

    private int men;
    private int budget;
    private List<Contract> contracts;
    private EnumOrientation heading;
    private int cost;
    private String status;
    private Extras extras;

    public Answer() {
        contracts = new ArrayList<>();
        extras = new Extras();
    }

    public int getMen() {
        return men;
    }

    public int getBudget() {
        return budget;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

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
        return extras.getRange();
    }

    public boolean getFound() {
        return extras.getFound();
    }

    public List<EnumBiome> getBiomes() {
        return extras.getBiomes();
    }

    public String getCreeks() {
        if (extras.getCreeks().size() >= 1)
            return extras.getCreeks().get(0);
        return null;
    }

    public String getSites() {
        if (extras.getSites().size() >= 1)
            return extras.getSites().get(0);
        return null;
    }

    public int getAltitude() {
        return extras.getAltitude();
    }

    public List<EnumResources> getResources() {
        return extras.getResources();
    }

    public int getAmount() {
        return extras.getAmount();
    }

    public int getProduction() {
        return extras.getProduction();
    }

    public EnumResources getKind() {
        return extras.getKind();
    }





}*/
