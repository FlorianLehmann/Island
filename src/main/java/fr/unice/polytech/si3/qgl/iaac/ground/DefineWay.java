package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.json.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class DefineWay implements State {

    private boolean wayDefine;
    private Deque<String> stack;



    public DefineWay() {
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

        while(!contracts.isPrimaryCompleted() && !(carte.hasResource(contracts.getPrimaryContract().getName()))) {
            contracts.remove(contracts.getPrimaryContract().getName());
        }
        if (!contracts.isPrimaryCompleted()) {
            if(!wayDefine) {
                tmp = contracts.getPrimaryContract().getName().toString();
                if ("FISH".equals(tmp)) {
                    //point = map.getACreek();
                    point = carte.getResource(contracts.getPrimaryContract().getName());

                } else {
                    point = carte.getResource(contracts.getPrimaryContract().getName());
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
            return new Explore();
        return this;
    }

}
