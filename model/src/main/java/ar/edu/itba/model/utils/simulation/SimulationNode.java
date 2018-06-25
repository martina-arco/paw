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

    public SimulationNode dispute(MatchStatus matchStatus) {
        int opponentDef = node.getAtt(otherTeam(possession), NodeAtt.DEF);
        int myPoss = node.getAtt(possession, NodeAtt.POSS);

        int sum = myPoss + opponentDef + 1;
        double nMyPoss = (double) myPoss / sum;

        boolean taken = grid.getRand().nextDouble() < nMyPoss;

        if (taken) {
            matchStatus.getEvents().add(new Event(0, node.getSNode(otherTeam(possession)).whoDidIt(), Event.Type.TACKLE, matchStatus.getMinute()));
        }

        return node.getSNode(taken ? otherTeam(possession) : possession);
    }

    SimulationNode shot(MatchStatus matchStatus) {
        double check = grid.getRand().nextDouble();
        int shot = node.getAtt(possession, NodeAtt.FIN) / (distanceToGoal() + 1);

        int sum = shot + opGK.getGoalKeeping() + node.getAtt(otherTeam(possession), NodeAtt.DEF);
        double norm = (double) shot / sum;
        boolean goal = check < norm;

        if (goal) {

            if (possession.equals(MyTeam.AWAY))
                matchStatus.setAwayScore(matchStatus.getAwayScore() + 1);
            else
                matchStatus.setHomeScore(matchStatus.getHomeScore() + 1);

            matchStatus.getEvents().add(new Event(0, whoDidIt(), Event.Type.SCORE, matchStatus.getMinute()));
            return grid.kickOff(otherTeam(possession));
        }

        matchStatus.getEvents().add(new Event(0, opGK, Event.Type.SAVE, matchStatus.getMinute()));
        return grid.goalKick(otherTeam(possession));
    }

    private int distanceToGoal() {
        return Point.manhattanSq(node.getPosition(), possession.equals(MyTeam.AWAY) ? new Point(0, 2) : new Point(3, 2));
    }

    public SimulationNode advance(MatchStatus matchStatus) {
        double chanceToShoot = distanceToGoal() == 0 ? 1.0 : 1 / (distanceToGoal() * 2);
        boolean shooting = grid.getRand().nextDouble() < chanceToShoot;

        if (distanceToGoal() < 2 || shooting)
            return shot(matchStatus);

        double random = grid.getRand().nextDouble();
        double accum = 0;
        SimulationNode last = null;
        for (SimulationArc arc : neighbors) {
            accum += arc.getWeight();
            if (random < accum)
                return arc.getNeighbor();
            last = arc.getNeighbor();
        }

        matchStatus.getEvents().add(new Event(0, whoDidIt(), Event.Type.PASS, matchStatus.getMinute()));

        return last;
    }

    private Player whoDidIt() {
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
}
