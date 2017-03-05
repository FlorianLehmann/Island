package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by sebde on 19/02/2017.
 */
public class DefineWaySecond implements State{
    private boolean wayDefine;
    private Deque<String> stack;
    private int numeroRessource=0;



    public DefineWaySecond() {
        wayDefine = false;
        stack = new ArrayDeque<>();
    }
    public DefineWaySecond(int numeroRessource){
        wayDefine=false;
        stack = new ArrayDeque<>();
        this.numeroRessource=numeroRessource;
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        EnumOrientation direction;
        String tmp;
        int coordX;
        int coordY;
        Point point;
        int possibleContrat=0;

        while(possibleContrat!=1 && !contracts.isSecondaryCompleted()) {
            if (!contracts.isSecondaryCompleted()) {
                for (EnumPrimaryResources ressource : ((EnumManufacturedResources) contracts.getSecondaryContract().getName()).getNeeded()) {
                    if (!(carte.hasResource(ressource))) {
                        contracts.remove(contracts.getSecondaryContract().getName());
                        possibleContrat=0;
                        break;
                    }
                    else possibleContrat=1;
                }
            }
        }

        if (!contracts.isSecondaryCompleted()) {
            if(!wayDefine) {
                tmp = ((EnumManufacturedResources)contracts.getSecondaryContract().getName()).getNeeded().get(numeroRessource).toString();
                if ("FISH".equals(tmp)) {
                    //point = carte.getACreek();
                    point = carte.getResource(((EnumManufacturedResources)contracts.getSecondaryContract().getName()).getNeeded().get(0));

                } else {
                    point = carte.getResource(((EnumManufacturedResources)contracts.getSecondaryContract().getName()).getNeeded().get(0));
                }

                coordX = point.x - men.getCoord().x;
                coordY = point.y - men.getCoord().y;

                if (coordX < 0)
                    direction = EnumOrientation.WEST;
                else
                    direction = EnumOrientation.EST;

                for (int i = 0; i < Math.abs(coordX) ; i++) {
                    stack.push(men.moveTo(direction.front()));
                }
                if (coordY < 0)
                    direction = EnumOrientation.SOUTH;
                else
                    direction = EnumOrientation.NORTH;
                for (int i = 0; i < Math.abs(coordY); i++) {
                    stack.push(men.moveTo(direction.front()));
                }
                wayDefine = true;
            }
            //// TODO: 18/02/2017
            TounerRond.init();
            if (stack.size() == 0) {
                return men.explore();
            }
            else {
                return stack.pop();
            }

        } else {
            return EnumJSON.STOP.toString("");
        }


    }

    @Override
    public State wait(ReadJSON json) {
        if (stack.size() == 0)
            return new ExploreSecond(numeroRessource);
        return this;
    }

}
