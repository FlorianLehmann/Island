package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.Carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoBudgetfield;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoHeadingField;
import fr.unice.polytech.si3.qgl.iaac.exceptions.NoPOIException;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    public ReadJSON(String json) {
        biomes = new LinkedList<>();
        jsonObject = new JSONObject(json);
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
        throw new UnsupportedOperationException();
    }

    public int initNbMen() {
        throw new UnsupportedOperationException();
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
            if (extras.has(SITES.toString())) {

                tab = extras.getJSONArray(SITES.toString());

                iterator = tab.iterator();

                while (iterator.hasNext())
                    siteID = (String) iterator.next();

            }
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
            }/*
            if (bio.has(AMOUNT.toString())) {
                informations.put(AMOUNT.toString(), bio.getInt(AMOUNT.toString()));
            }
            if (bio.has(RESOURCES.toString())) {

                resources.clear();
                tab = bio.getJSONArray(RESOURCES.toString());

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    resources.add(( ((JSONObject) iterator.next()).getString("resource")));


                }
            }*/
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
        return biomes;
    }

    public int getCost() {
        return cost;
    }
}
