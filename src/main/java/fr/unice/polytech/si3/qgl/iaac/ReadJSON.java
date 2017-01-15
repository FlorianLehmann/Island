package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource.EnumSecondaire;
import org.json.JSONArray;
import org.json.JSONObject;

import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;

import java.util.*;

public class ReadJSON {

    /**
     * Attributes
     */
    private static Map<String, Object> informations;
    private static List<String> contracts;
    private static List<String> resources;
    private static List<Integer> amount;

    private static List<String> biome;
    private JSONObject jsonobject;

    /**
     * Constructeur par défaut
     */
    public ReadJSON() {

        informations = new HashMap();
        contracts = new ArrayList();
        amount = new ArrayList();
        resources = new ArrayList();
        biome = new ArrayList();

    }

    public static List getContracts() {
        return contracts;
    }

    public static List<Integer> getAmount() {
        return amount;
    }

    public static int getCollect() {
        return (int) informations.get("amount");
    }

    /**
     * Lit une requete JSON et stocke les valeurs associees
     */
    public void read(String s) {
        if (!informations.isEmpty())
            informations.clear();
        jsonobject = new JSONObject(s);

        if (jsonobject.has(MEN.toString()))
            informations.put(MEN.toString(), jsonobject.getInt(MEN.toString()));

        if (jsonobject.has(BUDGET.toString()))
            informations.put(BUDGET.toString(), jsonobject.getInt(BUDGET.toString()));

        if (jsonobject.has(HEADING.toString()))
            informations.put(HEADING.toString(), jsonobject.getString(HEADING.toString()));

        if (jsonobject.has(CONTRACTS.toString())) {

            JSONObject jsonobject2;
            JSONArray array = jsonobject.getJSONArray(CONTRACTS.toString());
            Iterator iterator = array.iterator();
            Iterator<String> iterator_ressource;

            while (iterator.hasNext()) {

                jsonobject2 = (JSONObject) iterator.next();
                iterator_ressource = jsonobject2.keys();

                while (iterator_ressource.hasNext()) {
                    int am = jsonobject2.getInt(iterator_ressource.next());
                    String re = jsonobject2.getString(iterator_ressource.next());
                    if (!EnumSecondaire.isSecond(re)) {
                        amount.add(am);
                        contracts.add(re);
                    }

                }

            }

            for (int i = 0; i < contracts.size(); i++) {
                if ("FISH".equals(contracts.get(i))) {
                    int amount1 = amount.get(0);
                    String tmp1 = contracts.get(0);
                    contracts.set(0, contracts.get(i));
                    amount.set(0, amount.get(i));
                    contracts.set(i, tmp1);
                    amount.set(i, amount1);
                }

            }


        }
        if (jsonobject.has(EXTRAS.toString())) {
            JSONObject bio = jsonobject.getJSONObject(EXTRAS.toString());
            if (bio.has(RANGE.toString()))
                informations.put(RANGE.toString(), bio.getInt(RANGE.toString()));
            if (bio.has(FOUND.toString()))
                informations.put(FOUND.toString(), bio.getString(FOUND.toString()));
            JSONArray tab;
            Iterator iterator;
            if (bio.has(CREEKS.toString())) {
                tab = bio.getJSONArray(CREEKS.toString());

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    informations.put(CREEKS.toString(), iterator.next());
                }
            }
            if (bio.has(SITES.toString())) {


                tab = bio.getJSONArray(SITES.toString());

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    informations.put(SITES.toString(), iterator.next());

                }
            }
            if (bio.has(BIOMES.toString())) {

                biome.clear();

                tab = bio.getJSONArray(BIOMES.toString());

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    biome.add((String) iterator.next());

                }
            }
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
            }
        }

        if (jsonobject.has(COST.toString())) {
            informations.put(COST.toString(), jsonobject.getInt(COST.toString()));
        }


    }

    /**
     * @return informations
     */
    static public Map<String, Object> getInformations() {

        return informations;

    }

    static public List<String> getBiome() {

        return biome;

    }

    static public List<String> getResources() {

        return resources;

    }

}
