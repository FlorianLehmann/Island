package fr.unice.polytech.si3.qgl.iaac.ground.tools;

import fr.unice.polytech.si3.qgl.iaac.EnumJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.ArrayMap;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by florian on 03/03/2017.
 */
public class AStar {

    //TODO on ne gère pas le cas des coord negatives
    //si on en démarre pas dans la bonne zone ça plante

    private Deque<Point> way;
    private Point location;
    private Point target;
    private List<Node> open;
    private List<Node> close;
    private List<Node> neighbours;
    private ArrayMap edge;

    private class Node {
        private Node parent;
        private Point location;
        private int locationToCurrent;
        private int currentToTarget;

        private Node(Point location){
            this.location = location;
            currentToTarget = 0;
            locationToCurrent = 0;
        }

        private Node(Node parent, Point location){
            this.location = location;
            this.parent = parent;
            currentToTarget = 0;
            locationToCurrent = parent.locationToCurrent;
        }
    }

    public AStar(Point location, Point target, ArrayMap edge){
        this.location = location;
        open = new ArrayList<>();
        close = new ArrayList<>();
        neighbours = new ArrayList<>();
        this.edge = edge;
        way = new ArrayDeque<>();
        this.target = target;
        open.add(new Node(new Point(location)));
    }

    private int distanceManathan(Point location, Point target){
        return Math.abs(location.x - target.x) + Math.abs(location.y - target.y);
    }

    public void compute() {
        int indexOpen = 0;
        Node parent = open.get(indexOpen);
        parent.currentToTarget = distanceManathan(parent.location, target);
        close.add(parent);

        while(!open.isEmpty() && distanceManathan(parent.location, target)!=0) {

            //Ajout des nouveaux voisons
            addNeighbours(parent);
            open.remove(indexOpen);
            //On vérifie les cases
            for (int i = 0; i < neighbours.size(); i++) {
                int x = neighbours.get(i).location.x;
                int y = neighbours.get(i).location.y;
                if (!edge.isEdgeG(new Point(x,y)) && !isInList(close, neighbours.get(i).location)) {
                //if (!edge.isEdge(new Point(x,y)) && !isInList(close, neighbours.get(i).location)) {
                    if (!isInList(open, neighbours.get(i).location)){
                        neighbours.get(i).parent = parent;
                        open.add(neighbours.get(i));
                    }

                    if (isInList(open, neighbours.get(i).location) && neighbours.get(i).locationToCurrent < parent.locationToCurrent) {
                        remove(close, parent.location);
                        parent = neighbours.get(i);
                        remove(open, parent.location);
                    }

                }
            }
            neighbours.clear();

            //prendre la case qui a le cout le moins important
            int min = Integer.MAX_VALUE;
            indexOpen = 0;
            for (int i = 0; i < open.size(); i++) {
                open.get(i).currentToTarget = distanceManathan(open.get(i).location, target);//rajouter tardivement
                open.get(i).locationToCurrent = distanceManathan(location, open.get(i).location);
                int totalCost = open.get(i).currentToTarget + open.get(i).locationToCurrent;

                if (min >= totalCost){
                    min = totalCost;
                    indexOpen = i;
                }
            }
            parent = open.get(indexOpen);
            close.add(parent);
        }

        while(parent.location.x != location.x || parent.location.y!= location.y ){
            way.push(parent.location);
            parent = parent.parent;
        }
        //On ne tient pas compte de la case initiale
    }

    private boolean isInList(List<Node> list, Point point) {
        for(Node p: list){
            if (p.location.x == point.x && p.location.y == point.y) {
                return true;
            }
        }
        return false;
    }

    private void addNeighbours(Node parent){
        neighbours.add(new Node(parent, new Point(parent.location.x + 1, parent.location.y)));
        neighbours.add(new Node(parent, new Point(parent.location.x - 1, parent.location.y)));
        neighbours.add(new Node(parent, new Point(parent.location.x, parent.location.y + 1)));
        neighbours.add(new Node(parent, new Point(parent.location.x, parent.location.y - 1)));
    }

    private void remove(List<Node> list, Point point) {
        for(int i = 0; i < list.size(); i++){
            if (list.get(i).location.x == point.x && list.get(i).location.y == point.y) {
                list.remove(i);
                return;
            }
        }
    }

    public Deque<Point> getWay() {
        return way;
    }

}
