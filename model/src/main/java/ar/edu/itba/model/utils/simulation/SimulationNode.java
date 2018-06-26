package ar.edu.itba.model.utils.simulation;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.MatchStatus;
import ar.edu.itba.model.utils.Point;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static ar.edu.itba.model.utils.simulation.Grid.otherTeam;

public class SimulationNode {

    private final Player opGK;
    private final Grid grid;
    private final GridNode node;
    private final MyTeam possession;
    private Map<Player, Integer> influenceMap;
    private final Collection<SimulationArc> neighbors;

    public SimulationNode(GridNode node, MyTeam possession, Player opGK, Grid grid) {
        this.opGK = opGK;
        this.node = node;
        this.possession = possession;
        this.grid = grid;
        this.influenceMap = new HashMap<>();
        this.neighbors = new HashSet<>();
    }

    public Player whoDidIt() {
        double action = grid.getRand().nextDouble();
        int accum = 0;
        Player guilty = null;

        for (Map.Entry entry : influenceMap.entrySet()) {
            accum += (int) entry.getValue();
            if (action < accum) {
                guilty = (Player) entry.getKey();
            }
        }
        return guilty;
    }

    public int distanceToGoal() {
        return Point.manhattanSq(node.getPosition(), possession.equals(MyTeam.AWAY) ? new Point(0, 2) : new Point(3, 2));
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(!o.getClass().equals(SimulationNode.class))
            return false;
        SimulationNode other = (SimulationNode) o;
        return other.possession == possession && node.getPosition().equals(((SimulationNode) o).getNode().getPosition());
    }

    public String toString() {
        return node.toString() + "\t" + possession;
    }


    public Map<Player, Integer> getInfluenceMap() {
        return influenceMap;
    }

    public GridNode getNode() {
        return node;
    }

    public Collection<SimulationArc> getNeighbors() {
        return neighbors;
    }

    public MyTeam getPossession() {
        return possession;
    }

    public Grid getGrid() {
        return grid;
    }

    public Player getOpGK() {
        return opGK;
    }
}
