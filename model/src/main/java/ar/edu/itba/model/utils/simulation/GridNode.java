package ar.edu.itba.model.utils.simulation;

import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.Point;

import java.util.HashMap;
import java.util.Map;

public class GridNode {
    private final Point position;
    private SimulationNode home, away;
    private Map<MyTeam, Map<NodeAtt, Integer>> attributes;

    public GridNode(Point position) {
        this.position = position;
        attributes = new HashMap<>();
        attributes.put(MyTeam.HOME, new HashMap<NodeAtt, Integer>());
        attributes.put(MyTeam.AWAY, new HashMap<NodeAtt, Integer>());
        for (NodeAtt nodeAtt : NodeAtt.values()) {
            attributes.get(MyTeam.AWAY).put(nodeAtt, 0);
            attributes.get(MyTeam.HOME).put(nodeAtt, 0);
        }
    }

    public SimulationNode getSNode(MyTeam team) {
        return team.equals(MyTeam.AWAY) ? away : home;
    }

    public void setNode(MyTeam team, SimulationNode sNode) {
        if (team.equals(MyTeam.AWAY))
            away = sNode;
        else
            home = sNode;
    }

    public void setAtt(MyTeam team, NodeAtt att, int value) {
        attributes.get(team).put(att, value);
    }

    public Integer getAtt(MyTeam team, NodeAtt att) {
        return attributes.get(team).get(att);
    }

    public void accumAtt(MyTeam team, NodeAtt att, int value) {
        setAtt(team, att, getAtt(team, att) + value);
    }

    public void influence(MyTeam team, Player player, int count) {
        Map<Player, Integer> map = (team.equals(MyTeam.AWAY) ? away : home).getInfluenceMap();
        map.put(player, 100 / count);
        accumAtt(team, NodeAtt.DEF, player.getDefending() / count);
        accumAtt(team, NodeAtt.PASS, player.getPassing() / count);
        accumAtt(team, NodeAtt.FIN, player.getPassing() / count);
        accumAtt(team, NodeAtt.POSS, player.getSkillLevel() / count);
    }

    public String toString() {
        return "X: " + position.getX() + "\tY: " + position.getY() + "\t" + attributes.toString();
    }

    public Point getPosition() {
        return position;
    }

    public SimulationNode getHome() {
        return home;
    }

    public SimulationNode getAway() {
        return away;
    }
}
