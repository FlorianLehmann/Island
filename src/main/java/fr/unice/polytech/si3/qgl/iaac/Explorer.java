package fr.unice.polytech.si3.qgl.iaac;
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
    private List<Integer> verifcout;
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
		drone = new Drone(heading);
    }

    @Override
    public String takeDecision() {
        
        /*if ( etat == 0){
            if( drone.findIsland() )
                etat++;
            else {
                this.action = drone.getAction();
                break;}
            
        }
        if ( etat == 1){
            if (drone.parcourirIle()) {
                etat++;
            }
            else{
                this.action = drone.getAction();
                break;
            }
            
        } else this.action = "{ \"action\": \"stop\" }";*/
        
	switch(etat){

	case 0:
	    if( drone.findIsland() )
		etat++;
	    else
		this.action = drone.getAction();
	    break;
	case 1:
	    if (drone.parcourirIle()) {
		etat++;
	    }
	    else
		this.action = drone.getAction();
	    break;
	default:
	    this.action = "{ \"action\": \"stop\" }";
		break;
	 

	}
	if (budget < 19)
	    this.action = "{ \"action\": \"stop\" }";
	return this.action;
    }

    @Override
    public void acknowledgeResults(String s) {
	JSONObject jsonobject = new JSONObject(s);
	JSONObject jsonaction = new JSONObject(action);
	int i = 0;
	cost = 0; //modif a suppr!

	
	switch(jsonaction.getString("action")){
	    case "echo":
		if( jsonobject.has("cost"))
		    {			
			this.cost = jsonobject.getInt("cost");
		    }
		if( jsonobject.has("extras"))
		    {
			JSONObject bio = jsonobject.getJSONObject("extras");
			range = bio.getInt("range");
			found = bio.getString("found");
		    }

		    if ( jsonobject.has("status"))
			this.status = jsonobject.getString("status").equals("OK");


		    drone.setResult(found);
		    drone.setNbCase(range);
		    
		break;
	    case "scan":
            if( jsonobject.has("cost"))
            {
                this.cost = jsonobject.getInt("cost");
            }

		if ( jsonobject.has("status"))
		    this.status = jsonobject.getString("status").equals("OK");

		if( jsonobject.has("extras"))
		    {
			
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


			tab = bio.getJSONArray("creeks");

			iterator = tab.iterator();
			
			while( iterator.hasNext()){
			    drone.setIdCrique((String) iterator.next());
			    }

			tab = bio.getJSONArray("sites");

			iterator = tab.iterator();
			
			while( iterator.hasNext()){
			    drone.setIdPU( (String) iterator.next());
			}
            }
		break;
	    case "heading":
		if( jsonobject.has("cost"))
		    this.cost = jsonobject.getInt("cost");
		break;

	    case "fly":
		if( jsonobject.has("cost"))
		    this.cost = jsonobject.getInt("cost");
		break;
        case "stop":
            if( jsonobject.has("cost"))
                this.cost = jsonobject.getInt("cost");
            break;

	    }
	budget = budget - cost;

				

    }

    @Override
    public String deliverFinalReport() {
        return new String("CREEK:" + drone.getIdCrique()+"\n"+ "EMERGENCY:" + drone.getIdPU()+"\n"); //
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

    static public String getFound(){
	return found;
    }
    public int getRange(){
	return range;
    }
	

}
