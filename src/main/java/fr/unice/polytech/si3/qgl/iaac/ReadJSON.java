package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoBudgetfield;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoHeadingField;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome.OCEAN;

/**
 * Created by lehmann on 04/02/17.
 */
public class ReadJSON {



    private JSONObject jsonObject;
    private String siteID;
    private String creekID;
    private boolean found;
    private int range;
    private int cost;
    private List<EnumBiome> biomes;
    private List<String> resources;
    private int collect;

    public ReadJSON(String json) {
        biomes = new LinkedList<>();
        jsonObject = new JSONObject(json);
        resources = new ArrayList<>();
    }

    public Budget initBudget() {
        if (jsonObject.has(BUDGET.toString()))
            return new Budget(jsonObject.getInt(BUDGET.toString()));
        throw new NoBudgetfield();
    }

    public Drone initDrone() {
        if (jsonObject.has(HEADING.toString()))
            return new Drone(EnumOrientation.getEnumDirection(jsonObject.getString(HEADING.toString())));
        throw new NoHeadingField();
    }

    public Contracts initContracts() {
        Contracts contracts = new Contracts();
        if (jsonObject.has(CONTRACTS.toString())) {

            JSONObject jsonobject2;
            JSONArray array = jsonObject.getJSONArray(CONTRACTS.toString());
            Iterator iterator = array.iterator();
            Iterator<String> iterator_ressource;

            while (iterator.hasNext()) {

                jsonobject2 = (JSONObject) iterator.next();
                iterator_ressource = jsonobject2.keys();

                while (iterator_ressource.hasNext()) {
                    int am = jsonobject2.getInt(iterator_ressource.next());
                    String re = jsonobject2.getString(iterator_ressource.next());
                    if (EnumPrimaryResources.isPrimary(re)) {
                        contracts.add(new Contract(EnumPrimaryResources.getEnumPrimaryResources(re),am));
                    }
                    else {
                        contracts.add(new Contract(EnumManufacturedResources.getEnumManufacturedResources(re),am));
                    }

                }

            }
        }
        return contracts;
    }

    public int initNbMen() {
        if (jsonObject.has(MEN.toString()))
            return jsonObject.getInt(MEN.toString());
        throw new RuntimeException("No field for nbMen");
    }


    public void read(String json) {
        jsonObject = new JSONObject(json);
        if (jsonObject.has(EXTRAS.toString())) {
            JSONObject extras = jsonObject.getJSONObject(EXTRAS.toString());
            if (extras.has(RANGE.toString()))
                range = extras.getInt(RANGE.toString());
            if (extras.has(FOUND.toString()))
                found = GROUND.toString().equals(extras.getString(FOUND.toString()));
            else
                found = false;

            JSONArray tab;

            Iterator iterator;

            if (extras.has(CREEKS.toString())) {
                tab = extras.getJSONArray(CREEKS.toString());

                iterator = tab.iterator();

                while (iterator.hasNext())
                    creekID = (String) iterator.next();

            }
            else
                creekID = null;
            if (extras.has(SITES.toString())) {

                tab = extras.getJSONArray(SITES.toString());

                iterator = tab.iterator();

                while (iterator.hasNext())
                    siteID = (String) iterator.next();

            }
            else
                siteID = null;
            // on ne gère pas le cas ou il y a plusierus crique au même endroit car 3*3 cases
            //todo

            if (extras.has(BIOMES.toString())) {

                biomes.clear();

                tab = extras.getJSONArray(BIOMES.toString());

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    String str = (String) iterator.next();
                    biomes.add(EnumBiome.getEnumBiome(str));
                    if (!OCEAN.toString().equals(str)){
                        found = true;
                    }
                }
            }
            if (extras.has(AMOUNT.toString())) {
                collect = extras.getInt(AMOUNT.toString());
            }
            if (extras.has(PRODUCTION.toString())){
                collect = extras.getInt(PRODUCTION.toString());
            }
            if (extras.has(RESOURCES.toString())) {

                resources.clear();
                tab = extras.getJSONArray(RESOURCES.toString());

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    resources.add(( ((JSONObject) iterator.next()).getString("resource")));
                }
            }
        }

        if (jsonObject.has(COST.toString()))
            cost = jsonObject.getInt(COST.toString());

    }

    public boolean getGround(){
        return found;
    }

    public int getRange(){
        return range;
    }

    //todo return null ou exceptuion catch?
    public String getSiteID() {
        if (siteID == null)
            return null;
            //throw new NoPOIException("Site");
        return new String(siteID);
    }

    public String getCreekID() {
        if (creekID == null)
            return null;
            //throw new NoPOIException("Creek");
        return new String(creekID);
    }

    //todo non secure et ne fonctionne pas
    public List<EnumBiome> getBiomes() {
        List<EnumBiome> bio = new ArrayList<>();
        for (int i = 0; i < biomes.size(); i++) {
            bio.add(biomes.get(i));
        }
        return bio;
    }

    public int getCost() {
        return cost;
    }

    public int getCollect() {
        return collect;
    }

    public List<String> getResources() {
        return resources;
    }
}
