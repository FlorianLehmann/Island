package fr.unice.polytech.si3.qgl.iaac.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lehmann on 22/03/17.
 */
public class Extras {

    private int range;
    private String found;
    private List<EnumBiome> biomes;
    private List<String> creeks;
    private List<String> sites;
    private int altitude;
    private List<Resources> resources;
    private int amount;
    private int production;
    private EnumResources kind;

    public Extras() {
        resources = new ArrayList<>();
        creeks = new ArrayList<>();
        sites = new ArrayList<>();
        biomes = new ArrayList<>();
    }

    public int getRange() {
        return range;
    }

    public boolean getFound() {
        return "GROUND".equals(found);
    }

    public List<EnumBiome> getBiomes() {
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

    public List<EnumResources> getResources() {
        List<EnumResources> enumResources = new ArrayList<>();
        for (Resources resource : resources)
            enumResources.add(resource.getResource());
        return enumResources;
    }

    public int getAmount() {
        return amount;
    }

    public int getProduction() {
        return production;
    }

    public EnumResources getKind() {
        return kind;
    }


}
