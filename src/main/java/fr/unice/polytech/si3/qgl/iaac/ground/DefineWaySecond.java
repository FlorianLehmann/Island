package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by sebde on 19/02/2017.
 */
public class DefineWaySecond implements State{
    private boolean wayDefine;
    private Deque<String> stack;



    public DefineWaySecond() {
        wayDefine = false;
        stack = new ArrayDeque<>();
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        EnumOrientation direction;
        String tmp;
        int coordX;
        int coordY;
        Point point;
/*
        while(!contracts.isSecondaryCompleted() && !(carte.hasResource(contracts.getContract().getName()))) {
            contracts.remove(contracts.getContract().getName());
        }*/
        if (!contracts.isSecondaryCompleted()) {
            if(!wayDefine) {
                tmp = ((EnumManufacturedResources)contracts.getSecondaryContract().getName()).getNeeded().get(0).toString();
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
            return new ExploreSecond();
        return this;
    }
}
