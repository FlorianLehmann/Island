package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumBiome;
import org.json.JSONObject;

import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.BUDGET;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.HEADING;

/**
 * Created by lehmann on 04/02/17.
 */
public class ReadJSON {

    private JSONObject jsonObject;
    private String siteID;
    private String criqueID;
    private List<EnumBiome> biomes;

    public ReadJSON(String json) {
        jsonObject = new JSONObject(json);
    }

    public Budget initBudget() {
        if (jsonObject.has(BUDGET.toString()))
            return new Budget(jsonObject.getInt(BUDGET.toString()));
        throw new RuntimeException("No field for budget");
    }

    public Drone initDrone() {
        if (jsonObject.has(HEADING.toString()))
            return new Drone(EnumOrientation.getEnumDirection(jsonObject.getString(HEADING.toString())));
        throw new RuntimeException("No field for heading");
    }

    public Contracts initContracts() {
        throw new UnsupportedOperationException();
    }
    /*
    public Men initMen() {
        throw new UnsupportedOperationException();
    }

    public Carte initCarte() {
        throw new UnsupportedOperationException();
    }*/

    public boolean getGround(){
        throw new UnsupportedOperationException();
    }

    public int getRange(){
        throw new UnsupportedOperationException();
    }

    public String getSiteID() {
        return new String(siteID);
    }

    public String getCriqueID() {
        return new String(criqueID);
    }

    //todo non secure
    public List<EnumBiome> getBiomes() {
        return biomes;
    }
}
