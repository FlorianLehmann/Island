package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.ArrayMap;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
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
    private Contracts contracts;
    private Deque<Point> way;
    private Deque<EnumPrimaryResources> resourcesToCollect;

    private static final int MOVE = 0;
    private static final int EXPLORE = 1;
    private static final int EXPLOIT = 2;

    private static final Logger logger = LogManager.getLogger(ReachResources.class);


    public ReachResources(Carte carte) {
        state = MOVE;
        map = new ArrayMap(carte.getCases());
        way = new ArrayDeque<>();
        resourcesToCollect = new ArrayDeque<>();
        //TODO incapable d'aller chercher des poissons
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        this.contracts = contracts;
        switch (state) {
            case MOVE:
                if (way.isEmpty()) {
                    computeWay(men, contracts, carte);
                }
                return men.moveTo(findOrientation(men));
            case EXPLORE:
                return men.explore();
            case EXPLOIT:
                return men.exploit(resourcesToCollect.peek());
        }
        throw new UnsupportedOperationException("execute");
    }

    private void computeWay(Men men, Contracts contracts, Carte carte) {
        aStar = new AStar(men.getCoord(), chooseTarget(men,contracts, carte), map);
        aStar.compute();
        way = aStar.getWay();
    }

    private Point chooseTarget(Men men, Contracts contracts, Carte carte) {
        //return map.getRandomTarget();
        //TODO faux
        //si il n'y a plus de contrat choisir un contrat aléatoire
        Contract contract;
        do {
            contract  = contracts.getPrimaryContract();
        } while (contract.isCompleted());
        EnumResources resource = contract.getName();

        Point target = new Point(30,-90);
        //TODO TANT QUE LA RESSOURCES N'EST PAS PRÉSENTE SUR LA CARTE
        //TODO ON SUPPRIME LA RESSOURCE
        /*while(!contracts.isPrimaryCompleted() && !(carte.hasResource(contracts.getPrimaryContract().getName()))) {
        /*while(!contracts.isPrimaryCompleted() && !(map.hasResource(contracts.getPrimaryContract().getName()))) {
            contracts.remove(contracts.getPrimaryContract().getName());
        }*/
        if (carte.hasResource(resource)) {
            target = carte.getResource(resource);
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

        switch (state) {
            case MOVE:
                state = EXPLORE;
                break;
            case EXPLORE:
                for (String resource: json.getResources())
                    if (contracts.containRessource(((EnumPrimaryResources) EnumPrimaryResources.getEnumPrimaryResources(resource))))
                        resourcesToCollect.add((EnumPrimaryResources) EnumPrimaryResources.getEnumPrimaryResources(resource));

                if (!resourcesToCollect.isEmpty()) {
                    state = EXPLOIT;
                }
                else {
                    state = MOVE;
                }
                break;
            case EXPLOIT:
                contracts.addColectedContract(json.getCollect(), resourcesToCollect.pop());

                if (resourcesToCollect.isEmpty()) {
                    state = MOVE;
                }
                break;
        }
        return this;
    }
}
