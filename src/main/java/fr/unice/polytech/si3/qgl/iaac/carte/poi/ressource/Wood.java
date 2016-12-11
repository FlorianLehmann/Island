package sample.bot.carte.poi.ressource;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class Wood {

    private static List<Point> wood;

    public Wood() {
	wood = new ArrayList();
    }

    static public void addWood(Point point) {
	wood.add(new Point((int)point.getX(),(int)point.getY()));
    }
    static public Point getNearest(Point point) {
	double norme = 0;
	double Min;
	int index = 0;
	Min = Math.sqrt(Math.abs(((point.getX()-wood.get(0).getX())*(point.getX()-wood.get(0).getX())) +  ((point.getY()-wood.get(0).getY())*(point.getY()-wood.get(0).getY())))); 
	for (int i = 0; i < wood.size() ; i++) {
	    norme = Math.sqrt(Math.abs(((point.getX()-wood.get(i).getX())*(point.getX()-wood.get(i).getX())) +  ((point.getY()-wood.get(i).getY())*(point.getY()-wood.get(i).getY()))));
	    if (norme < Min ) {
		Min = norme;
		index = i;
	    }
	}
	return wood.get(index);
    }
    
}
