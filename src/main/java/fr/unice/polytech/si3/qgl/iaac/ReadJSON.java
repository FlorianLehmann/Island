package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource.EnumSecondaire;
import org.json.JSONArray;
import org.json.JSONObject;

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

        if (jsonobject.has("men"))
            informations.put("men", jsonobject.getInt("men"));

        if (jsonobject.has("budget"))
            informations.put("budget", jsonobject.getInt("budget"));

        if (jsonobject.has("heading"))
            informations.put("heading", jsonobject.getString("heading")); // retourner un enumDirection

        if (jsonobject.has("contracts")) {

            JSONObject jsonobject2;
            JSONArray array = jsonobject.getJSONArray("contracts");
            Iterator iterator = array.iterator();
            Iterator<String> iterator_ressource;
            String tmp;

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
                if (contracts.get(i).equals("FISH")) {
                    int amount1 = amount.get(0);
                    String tmp1 = contracts.get(0);
                    contracts.set(0, contracts.get(i));
                    amount.set(0, amount.get(i));
                    contracts.set(i, tmp1);
                    amount.set(i, amount1);
                }

            }


        }
        if (jsonobject.has("extras")) {
            JSONObject bio = jsonobject.getJSONObject("extras");
            if (bio.has("range"))
                informations.put("range", bio.getInt("range"));
            if (bio.has("found"))
                informations.put("found", bio.getString("found"));
            JSONArray tab;
            Iterator iterator;
            if (bio.has("creeks")) {
                tab = bio.getJSONArray("creeks");

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    informations.put("creeks", iterator.next());
                }
            }
        /*else { informations.remove("creeks"); }*/
            if (bio.has("sites")) {


                tab = bio.getJSONArray("sites");

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    informations.put("sites", iterator.next());

                }
            }
            if (bio.has("biomes")) {

                biome.clear();

                tab = bio.getJSONArray("biomes");
                //informations.put("biomes", new ArrayList<String>());

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    biome.add((String) iterator.next());

                }
            }
            if (bio.has("amount")) {
                informations.put("amount", bio.getInt("amount"));
            }
            if (bio.has("resources")) {

                resources.clear();
                tab = bio.getJSONArray("resources");

                iterator = tab.iterator();

                while (iterator.hasNext()) {
                    resources.add(((String) ((JSONObject) iterator.next()).getString("resource")));


                }
            }
        /*else { informations.remove("sites"); }*/
        }

        if (jsonobject.has("cost")) {
            informations.put("cost", jsonobject.getInt("cost"));
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
