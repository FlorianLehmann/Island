import eu.ace_design.island.bot.IExplorerRaid;
import org.json.*;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Explorer implements IExplorerRaid {

    static private int men;
    static private List<Men> menList;
    static private int budget;
    static private String heading;
    static private Map<String, Integer> contracts;
    static private String resultat;
    private String action;
    private int cost;
    private int range;
    static private String found;
    private boolean status;
    private Drone drone;
    private int etat;
    private String []biomes;

    @Override
    public void initialize(String s) {
	resultat = new String();
	action = new String();
	cost = 0;
	etat = 0;
	range = 0;
	found = new String();
	status = true;// est-ce que le staus est verifie
	drone = new Drone();

	JSONObject jsonobject = new JSONObject(s);
	if (jsonobject.has("men"))
	{
	    this.men = jsonobject.getInt("men");
	    menList = new LinkedList<Men>();
	    for( int i = 0; i < men; i++)
		menList.add(new Men());
	}
	if (jsonobject.has("budget"))
	    this.budget = jsonobject.getInt("budget");
	if (jsonobject.has("heading"))
	    this.heading = jsonobject.getString("heading");
	if (jsonobject.has("contracts"))
	    {
		String tmp;
		JSONArray array = jsonobject.getJSONArray("contracts");
		JSONObject jsonobj = new JSONObject();
		contracts = new HashMap<String, Integer>();
		Iterator<String> iterator;
		for( int i = 0; i < array.length() ;i++){
		    jsonobj = array.getJSONObject(i);
		    iterator = jsonobj.keys();
		    while( iterator.hasNext() ){
			tmp = iterator.next();
			contracts.put(jsonobj.getString(iterator.next()), jsonobj.getInt(tmp));
		    }
		}

	    }

    }

    @Override
    public String takeDecision() {
	switch(etat){

	case 0:
	    if( drone.findIsland() )
		etat++;
	    else
		this.action = drone.getAction();
	    break;
	case 1:
	    this.action = "{ \"action\": \"stop\" }";
		break;
	default:
	    this.action = "{ \"action\": \"stop\" }";
		break;


	}
	return this.action;
    }

    @Override
    public void acknowledgeResults(String s) {
	JSONObject jsonobject = new JSONObject(s);
	JSONObject jsonaction = new JSONObject(action);
	int i = 0;
<<<<<<< HEAD
	
	switch(jsonaction.getString("action"))
=======

	switch(action)
>>>>>>> 7fbb919ac57f31004579f45ac022dd48b4ef8c6d
	    {
	    case "echo":
		if( jsonobject.has("cost"))
		    this.cost = jsonobject.getInt("cost");
		if( jsonobject.has("extras"))
		    {
			JSONArray array = jsonobject.getJSONArray("extras");
			range = array.getInt(0);
			found = array.getString(1);
		    }

		    if ( jsonobject.has("status"))
			this.status = jsonobject.getString("status").equals("OK");


		    drone.setResult(found);
		    drone.setNbCase(range);

		break;
	    case "scan":
		if( jsonobject.has("cost"))
		    this.cost = jsonobject.getInt("cost");

		if ( jsonobject.has("status"))
		    this.status = jsonobject.getString("status").equals("OK");

		if( jsonobject.has("extras"))
		    {
<<<<<<< HEAD
			
			JSONObject bio = jsonobject.getJSONObject("extras");
			JSONArray tab = bio.getJSONArray("biomes");

			Iterator iterator = tab.iterator();
			
			while( iterator.hasNext()){
			    if (! (iterator.next()).equals("OCEAN") ){
				drone.setResult("GROUND");
				found = "GROUND";
			    }
			    i++;
			}
		    }		    
		    
		    
=======
			JSONArray array = jsonobject.getJSONArray("extras");
			biomes =(String []) array.get(0);
		    }
		while( i < biomes.length){
		    if (! biomes[i].equals("OCEAN") )
			drone.setResult("GROUND");
		    i++;
		}



>>>>>>> 7fbb919ac57f31004579f45ac022dd48b4ef8c6d
		//manque creek
		//manque site

		break;

	    }

    }

    @Override
    public String deliverFinalReport() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
    static public String getResult()
    {
	return resultat;
    }
    static public int getNumberMen()
    {
	return men;
    }
    static public int getBudget()
    {
	return budget;
    }
    static public String getHeading()
    {
	return heading;
    }
    static public List<Men> getMens()
    {
	return menList;
    }
    static public Map<String, Integer> getContracts()
    {
	return contracts;
    }
<<<<<<< HEAD
    static public String getFound(){
	return found;
    }
	
=======

>>>>>>> 7fbb919ac57f31004579f45ac022dd48b4ef8c6d
}
