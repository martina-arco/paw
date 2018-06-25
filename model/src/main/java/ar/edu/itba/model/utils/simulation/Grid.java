package ar.edu.itba.model.utils.simulation;

import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import static ar.edu.itba.model.utils.simulation.MyTeam.AWAY;
import static ar.edu.itba.model.utils.simulation.MyTeam.HOME;

public class Grid {
    private static final int pitchSize = 4;
    private GridNode matrix[][];
    private Player homeGK, awayGK;
    private Random rand;

    public static MyTeam otherTeam(MyTeam possession){
        return possession == AWAY? AWAY:HOME;
    }

    public Grid(Formation home, Formation away) {

        for (Map.Entry entry : home.getStarters().entrySet()) {
            Point point = ((Point) entry.getValue());
            if (point.getY() == 4 && point.getX() == 0)
                homeGK = (Player) entry.getKey();
        }

        for (Map.Entry entry : away.getStarters().entrySet()) {
            Point point = ((Point) entry.getValue());
            if (point.getY() == 4 && point.getX() == 0)
                awayGK = (Player) entry.getKey();
        }

        matrix = new GridNode[pitchSize][pitchSize];
        for (int x = 0; x < pitchSize; x++) {
            for (int y = 0; y < pitchSize; y++) {
                matrix[x][y] = new GridNode(new Point(x, y));
                SimulationNode sNodeHome = new SimulationNode(matrix[x][y], MyTeam.HOME, homeGK, this);
                SimulationNode sNodeAway = new SimulationNode(matrix[x][y], MyTeam.AWAY, awayGK, this);
                matrix[x][y].setNode(MyTeam.HOME, sNodeHome);
                matrix[x][y].setNode(MyTeam.AWAY, sNodeAway);
            }
        }

        rand = new Random(System.nanoTime());

        setFormation(MyTeam.HOME, home);
        setFormation(MyTeam.AWAY, away);

        configureNeighbors(home.getAttitude(), away.getAttitude());
    }

    public SimulationNode kickOff(MyTeam team) {
        double check = rand.nextDouble();
        if (team.equals(MyTeam.AWAY)) {
            return check < 0.5 ? matrix[2][1].getNode(AWAY) : matrix[2][2].getNode(AWAY);
        }
        return check < 0.5 ? matrix[1][1].getNode(HOME) : matrix[1][2].getNode(HOME);
    }

    public SimulationNode goalKick(MyTeam team) {
        double check = rand.nextDouble();
        if (team.equals(MyTeam.AWAY)) {
            return check < 0.5 ? matrix[3][1].getNode(AWAY) : matrix[3][2].getNode(AWAY);
        }
        return check < 0.5 ? matrix[0][1].getNode(HOME) : matrix[0][2].getNode(HOME);
    }

    public SimulationNode getNode(Point position, MyTeam team){
        if(team.equals(AWAY))
            return matrix[position.getX()][position.getY()].getNode(AWAY);
        return matrix[position.getX()][position.getY()].getNode(HOME);
    }

    private void setFormation(MyTeam team, Formation formation) {
        for (Map.Entry entry : formation.getStarters().entrySet()) {
            playerInfluence(team, (Point) entry.getValue(), (Player) entry.getKey());
        }
    }

    private void nodeNeighbors(MyTeam team, SimulationNode node, int attitude) {
        int x = node.getNode().getPosition().getX(), y = node.getNode().getPosition().getY();
        Collection<SimulationNode> neighbors = new HashSet<>();
        int accum = 1;

        for (int xx = 0; xx < pitchSize; xx++) {
            for (int yy = 0; yy < pitchSize; yy++) {
                int auxX;
                if (team.equals(MyTeam.HOME)) {
                    auxX = xx + 1;
                    if (auxX < pitchSize && auxX > x && !matrix[auxX][yy].getNode(HOME).getInfluenceMap().isEmpty()) {
                        neighbors.add(matrix[auxX][yy].getNode(HOME));
                        accum += node.getNode().getAtt(team, NodeAtt.PASS) * attitude / Point.manhattanSq(new Point(x, y), new Point(auxX, yy));
                    }
                } else {
                    auxX = pitchSize - xx - 1;
                    if (auxX >= 0 && auxX < x && !matrix[auxX][yy].getNode(AWAY).getInfluenceMap().isEmpty()) {
                        neighbors.add(matrix[auxX][yy].getNode(AWAY));
                        accum += node.getNode().getAtt(team, NodeAtt.PASS) * attitude / Point.manhattanSq(new Point(x, y), new Point(auxX, yy));
                    }
                }
            }
        }

        for (final SimulationNode sNode : neighbors) {
            double distance = Point.manhattanSq(sNode.getNode().getPosition(), new Point(x, y));
            int passing = matrix[x][y].getAtt(team, NodeAtt.PASS);
            final double normalizedWeight = passing * 100 * attitude / (accum * distance);
            node.getNeighbors().add(new SimulationArc() {
                @Override
                public double getWeight() {
                    return normalizedWeight;
                }

                @Override
                public SimulationNode getNeighbor() {
                    return sNode;
                }
            });
        }

    }

    public void configureNeighbors(int homeAttitude, int awayAttitude) {
        for (int x = 0; x < pitchSize; x++) {
            for (int y = 0; y < pitchSize; y++) {
                nodeNeighbors(MyTeam.HOME, matrix[x][y].getNode(HOME), homeAttitude);
                nodeNeighbors(MyTeam.AWAY, matrix[x][y].getNode(AWAY), awayAttitude);
            }
        }
    }

    private void playerInfluence(MyTeam team, Point position, Player player) {
        int x = position.getX(), y = position.getY(), count = 0;

        if (team == MyTeam.AWAY) {
            x = pitchSize * 2 - x;
            y = pitchSize * 2 - y;
        }

        if (x == 0 || x == 8 || y == 0 || y == 8)
            return;

        boolean splitX = (x % 2 == 0), splitY = (y % 2 == 0);
        Collection<GridNode> nodesToInfluence = new HashSet<>();

        x = x / 2;
        y = y / 2;

        if (splitX) {
            if (splitY) {
                nodesToInfluence.add(matrix[x - 1][y]);
                nodesToInfluence.add(matrix[x][y - 1]);
                nodesToInfluence.add(matrix[x - 1][y - 1]);
                nodesToInfluence.add(matrix[x][y]);
                count = 4;
            } else {
                nodesToInfluence.add(matrix[x - 1][y]);
                nodesToInfluence.add(matrix[x][y]);
                count = 2;
            }
        } else {
            if (splitY) {
                nodesToInfluence.add(matrix[x][y - 1]);
                nodesToInfluence.add(matrix[x][y]);
                count = 2;
            } else {
                nodesToInfluence.add(matrix[x][y]);
                count = 1;
            }
        }

        for (GridNode node : nodesToInfluence) {
            node.influence(team, player, count);
        }

    }

    public Random getRand() {
        return rand;
    }
}
