package sample.bot;

import org.json.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class ReadJSON {

    /**
     *
     * Attributes
     *
     */
    private static Map<String, Object> informations;
    private JSONObject jsonobject;

    /**
     *
     * Constructeur par défaut
     *
     */
    public ReadJSON() {

	informations = new HashMap();
	
    }


    /**
     *
     * Lit une requete JSON et stocke les valeurs associees
     *
     */
    public void read(String s) {
        if(!informations.isEmpty())
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
	    
	    while(iterator.hasNext()) {

		jsonobject2 = (JSONObject) iterator.next();
		iterator_ressource = jsonobject2.keys();
		
		while(iterator_ressource.hasNext()) {

		    tmp = iterator_ressource.next();
		    informations.put(tmp, jsonobject2.get(tmp));
		    
		}

	    }
		

	}
	if(jsonobject.has("extras"))
	    {
		JSONObject bio = jsonobject.getJSONObject("extras");
                if( bio.has("range"))
                    informations.put("range", bio.getInt("range"));
                if( bio.has("found"))
                    informations.put("found", bio.getString("found"));
		JSONArray tab;
		Iterator iterator;
		if (bio.has("creeks")) {
		tab = bio.getJSONArray("creeks");

			iterator = tab.iterator();
			
			while( iterator.hasNext()){
			    informations.put("creeks", iterator.next());
			}
		}
		/*else { informations.remove("creeks"); }*/
		if (bio.has("sites")) {
		    
	     

			tab = bio.getJSONArray("sites");

			iterator = tab.iterator();
			
			while( iterator.hasNext()){
			    informations.put("sites", iterator.next());
			    
			}
		}
		/*else { informations.remove("sites"); }*/
	    }
        
        if( jsonobject.has("cost"))
        {
            informations.put("cost", jsonobject.getInt("cost"));
        }
	    
	    
	
    }

    /**
     *
     * @return informations
     *
     */
    static public Map<String, Object> getInformations() {

	return informations;

    }
	
}
