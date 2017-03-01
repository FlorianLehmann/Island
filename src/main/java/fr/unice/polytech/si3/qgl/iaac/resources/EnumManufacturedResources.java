package fr.unice.polytech.si3.qgl.iaac.resources;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by florian on 09/02/2017.
 */
public enum EnumManufacturedResources implements EnumResources {

    GLASS(new ArrayList<EnumPrimaryResources>(Arrays.asList(EnumPrimaryResources.QUARTZ,EnumPrimaryResources.WOOD)),new ArrayList<Integer>(Arrays.asList(10,5)),1),
    INGOT(new ArrayList<EnumPrimaryResources>(Arrays.asList(EnumPrimaryResources.ORE,EnumPrimaryResources.WOOD)),new ArrayList<Integer>(Arrays.asList(5,5)),1),
    LEATHER(new ArrayList<EnumPrimaryResources>(Arrays.asList(EnumPrimaryResources.FUR)),new ArrayList<Integer>(Arrays.asList(3)),1),
    PLANK(new ArrayList<EnumPrimaryResources>(Arrays.asList(EnumPrimaryResources.WOOD)),new ArrayList<Integer>(Arrays.asList(1)),4),
    RUM(new ArrayList<EnumPrimaryResources>(Arrays.asList(EnumPrimaryResources.SUGAR_CANE,EnumPrimaryResources.FRUITS)),new ArrayList<Integer>(Arrays.asList(10,1)),1);

    List<EnumPrimaryResources> needed;
    List<Integer> nbNeeded;
    int nbCreated;

    EnumManufacturedResources(List<EnumPrimaryResources> needed,List<Integer> nbNeeded,int nbCreated) {
        this.needed=needed;
        this.nbNeeded=nbNeeded;
        this.nbCreated=nbCreated;
    }

    public boolean isPrimary() {
        return false;
    }

    @Override
    public String toString() {
        return name();
    }

    public static EnumResources getEnumManufacturedResources(String resource) {
        return EnumManufacturedResources.valueOf(resource);
    }

    public boolean isManufactured(String resource) {
        List<EnumManufacturedResources> resources = Arrays.asList(EnumManufacturedResources.values());
        for (int i = 0; i < resources.size(); i++)
            if (resources.get(i).name().equals(resource))
                return true;
        return false;
    }

    public List<EnumPrimaryResources> getNeeded(){
        return needed;
    }
    public List<Integer> getNbNeeded(){
        return nbNeeded;
    }
    public int getNbCreated(){
        return nbCreated;
    }
}
