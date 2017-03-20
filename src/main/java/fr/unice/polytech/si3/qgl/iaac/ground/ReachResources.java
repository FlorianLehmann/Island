package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.ArrayMap;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ground.tools.AStar;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by florian on 10/03/2017.
 */
public class ReachResources implements State {

    private int state;
    private ArrayMap map;
    private AStar aStar;
    private Deque<Point> way;

    private static final int MOVE = 0;
    private static final int EXPLORE = 1;
    private static final int EXPLOIT = 2;

    private static final Logger logger = LogManager.getLogger(ReachResources.class);


    public ReachResources(Carte carte) {
        state = MOVE;
        map = new ArrayMap(carte.getCases());
        way = new ArrayDeque<>();
        way.add(new Point(carte.getACreek().x , carte.getACreek().y +1));
        way.add(new Point(carte.getACreek().x , carte.getACreek().y +1));
        way.add(new Point(carte.getACreek().x , carte.getACreek().y +1));
        //TODO on arrive dans directement hors de l'eau donc on reste hors de la carte
        //incapable d'aller chercher des poissons
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        switch (state) {
            case MOVE:
                if (way.isEmpty()) {
                    computeWay(men, contracts, carte);
                    state = EXPLORE;
                }
                //state = EXPLORE;
                return men.moveTo(findOrientation(men));

            case EXPLORE:
                state = MOVE;
                return men.explore();
            case EXPLOIT:
                state = MOVE;
                return men.explore();

        }
        throw new UnsupportedOperationException("execute");
    }

    private void computeWay(Men men, Contracts contracts, Carte carte) {
        aStar = new AStar(men.getCoord(), chooseTarget(men,contracts, carte), map);
        aStar.compute();
        way = aStar.getWay();
    }

    private Point chooseTarget(Men men, Contracts contracts, Carte carte) {
        //TODO faux
        //si il n'y a plus de contrat choisir un contrat aléatoire
        Contract contract  = contracts.getPrimaryContract();
        EnumResources resource = contract.getName();
        Point target = new Point(70,-60);
        if (carte.hasResource(EnumPrimaryResources.WOOD)) {
            //logger.info("BOIS");
            target = carte.getResource(EnumPrimaryResources.WOOD);
        }
        logger.info("MEN" + men.getCoord());
        logger.info("TARGET" + target);
        return target;
    }

    private EnumOrientation findOrientation(Men men) {
        EnumOrientation orientation;
        if (men.getCoord().x - way.peek().x > 0) {
            orientation = EnumOrientation.WEST;
        }
        else if (men.getCoord().x - way.peek().x < 0){
            orientation = EnumOrientation.EST;
        }
        else if (men.getCoord().y - way.peek().y < 0) {
            orientation = EnumOrientation.NORTH;
        }
        else {
            orientation = EnumOrientation.SOUTH;
        }
        way.pop();
        return orientation;
    }

    @Override
    public State wait(ReadJSON json) {
        return this;
    }
}
