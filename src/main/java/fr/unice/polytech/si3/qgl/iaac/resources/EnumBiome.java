package fr.unice.polytech.si3.qgl.iaac.resources;


import java.util.ArrayList;
import java.util.List;


import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by Quentin on 09/02/2017.
 */
public enum EnumBiome {



    OCEAN("OCEAN",FISH),
    LAKE("LAKE", FISH),
    BEACH("BEACH" ,QUARTZ),
    GRASSLAND("GRASSLAND", FUR),
    MANGROVE("MANGROVE" ,FLOWER,WOOD),
    TROPICAL_RAIN_FOREST("TROPICAL_RAIN_FOREST" ,FRUITS,SUGAR_CANE,WOOD),
    TROPICAL_SEASONAL_FOREST("TROPICAL_SEASONAL_FOREST" ,FRUITS,SUGAR_CANE,WOOD),
    TEMPERATE_DECIDUOUS_FOREST("TEMPERATE_DECIDUOUS_FOREST", WOOD),
    TEMPERATE_RAIN_FOREST("TEMPERATE_RAIN_FOREST", FUR,WOOD),
    TEMPERATE_DESERT("TEMPERATE_DESERT" ,ORE,QUARTZ),
    TAIGA("TAIGA" ,WOOD),
    SNOW("SNOW"),
    TUNDRA("TUNDRA", FUR),
    ALPINE("ALPINE",FLOWER,ORE),
    GLACIER("GLACIER",FLOWER,ORE),
    SHRUBLAND("SHRUBLAND", FUR),
    SUB_TROPICAL_DESERT("SUB_TROPICAL_DESERT", ORE,QUARTZ);

    private final List<EnumResources> resources;
    private final String name;

    EnumBiome (String name, EnumResources ... resources){
        this.name = name;
        this.resources = new ArrayList<>();
        for(EnumResources resource : resources){
            this.resources.add(resource);
        }
    }

    public List<EnumResources> getResources(){
        return resources;
    }

    @Override
    public String toString() {
        return name;
    }

    public static EnumBiome getEnumBiome(String name){
        for(EnumBiome biome : EnumBiome.values())
            if (biome.toString().equals(name))
                return biome;
        throw new RuntimeException("Le biome n'existe pas");
    }
}
