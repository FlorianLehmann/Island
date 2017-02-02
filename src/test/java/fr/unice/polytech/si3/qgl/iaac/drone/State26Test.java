package fr.unice.polytech.si3.qgl.iaac.drone;

        import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertTrue;


        import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
        import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
        import org.junit.*;

/**
 * Created by Quentin on 11/12/2016.
 */
public class State26Test {
    private ReadJSON read;
    private Drone drone;
    private Carte carte;
    private String lastDirection;

    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        drone = new Drone(NORD, carte);
        drone.setState(new Land());

    }


}
