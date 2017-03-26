package fr.unice.polytech.si3.qgl.iaac.resources;


import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by Quentin on 09/02/2017.
 */
public enum EnumBiome {

    OCEAN(FISH),
    LAKE(FISH),
    BEACH(QUARTZ),
    GRASSLAND(FUR),
    MANGROVE(FLOWER,WOOD),
    TROPICAL_RAIN_FOREST(FRUITS,SUGAR_CANE,WOOD),
    TROPICAL_SEASONAL_FOREST(FRUITS,SUGAR_CANE,WOOD),
    TEMPERATE_DECIDUOUS_FOREST(WOOD),
    TEMPERATE_RAIN_FOREST(FUR,WOOD),
    TEMPERATE_DESERT(ORE,QUARTZ),
    TAIGA(WOOD),
    SNOW,
    TUNDRA(FUR),
    ALPINE(FLOWER,ORE),
    GLACIER(FLOWER,ORE),
    SHRUBLAND(FUR),
    SUB_TROPICAL_DESERT(ORE,QUARTZ);

    private final List<EnumResources> resources;

    EnumBiome (EnumResources ... resources){
        this.resources = new ArrayList<>();
        for(EnumResources resource : resources){
            this.resources.add(resource);
        }
    }

    public static EnumBiome getEnumBiome(String name) {
        return valueOf(name);
    }

    public List<EnumResources> getResources(){
        return resources;
    }

    @Override
    public String toString() {
        return name();
    }
}
