package ar.edu.itba.services;

import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.model.*;
import ar.edu.itba.model.utils.Point;

import java.util.*;

public class SimulationServiceImpl implements SimulationService {

    private static final int pitchSize = 4;

    public enum NodeAtt{
        DEF, PASS, FIN, POSS
    }

    public enum MyTeam{
        HOME, AWAY
    }

    private MyTeam otherTeam(MyTeam team){
        return team.equals(MyTeam.AWAY)?MyTeam.HOME:MyTeam.AWAY;
    }

    private final Set<MatchThread> playingMatches;

    public SimulationServiceImpl() {
        this.playingMatches = new HashSet<>();
    }

    public Grid createGrid(Formation home, Formation away){
        Grid grid = new Grid(home, away);
        grid.setFormation(MyTeam.HOME,home);
        grid.setFormation(MyTeam.AWAY,away);
        return grid;
    }

    public void playMatch(Formation home, Formation away){
        MatchThread matchThread = new MatchThread(null,home,away);
        matchThread.run();
    }

    @Override
    public void simulateFixture(List<Match> matches) {
        for(Match match : matches){
            MatchThread aux = new MatchThread(match);
            playingMatches.add(aux);
            aux.run();
        }
    }

    private class MatchThread extends Thread {
        private final Match match;
        private List<Event> events;
        Formation home, away;

        private MatchThread(Match match) {
            super();
            this.match = match;
            this.events = new ArrayList<>();
        }

        public MatchThread(Match match, Formation home, Formation away){
            this.match = match;
            this.home = home;
            this.away = away;
        }

        @Override
        public void run(){
            simulate(home,away);
        }

        private void simulate(Formation home, Formation away){
            //Team home = match.getHome(), away = match.getAway();
            Grid matchGrid = new Grid(home, away);
            SimulationNode currentState = matchGrid.kickOff(MyTeam.HOME),lastState = null;

            int minute = 0;
            while(minute <= 90){

                currentState = currentState.dispute(minute,events);

                if(currentState.equals(lastState)){
                    currentState = currentState.advance(minute,events);
                }else {
                    lastState = currentState;
                }

                System.out.println("Minute: " + minute + "\t" + currentState);


                minute++;

                /*
                try {
                    wait();
                } catch (InterruptedException e) {
                    minute++;
                }

                lastState = currentState;
                */
            }
        }

        public List<Event> getEvents(){
            List<Event> aux = events;
            events = new ArrayList<>();
            return aux;
        }

        public boolean equals(Object o){
            if(o == null)
                return false;
            if(o == this)
                return true;
            if(!o.getClass().equals(MatchThread.class))
                return false;
            MatchThread aux = (MatchThread) o;
            return aux.match.equals(match);
        }
    }

    private class GridNode {
        private final Point position;
        private SimulationNode home, away;
        private Map<MyTeam,Map<NodeAtt,Integer>> attributes;

        private GridNode(Point position) {
            this.position = position;
            attributes = new HashMap<>();
            attributes.put(MyTeam.HOME,new HashMap<NodeAtt, Integer>());
            attributes.put(MyTeam.AWAY, new HashMap<NodeAtt, Integer>());
            for(NodeAtt nodeAtt : NodeAtt.values()){
                    attributes.get(MyTeam.AWAY).put(nodeAtt, 0);
                    attributes.get(MyTeam.HOME).put(nodeAtt, 0);
            }
        }

        public SimulationNode getSNode(MyTeam team){
            return team.equals(MyTeam.AWAY)?away:home;
        }

        private void setNode(MyTeam team, SimulationNode sNode){
            if(team.equals(MyTeam.AWAY))
                away = sNode;
            else
                home = sNode;
        }

        private void setAtt(MyTeam team, NodeAtt att, int value){
            attributes.get(team).put(att,value);
        }

        private Integer getAtt(MyTeam team, NodeAtt att){
            return attributes.get(team).get(att);
        }

        private void accumAtt(MyTeam team, NodeAtt att, int value){
            setAtt(team,att, getAtt(team, att) + value);
        }

        private void influence(MyTeam team, Player player, int count){
            Map<Player,Integer> map = (team.equals(MyTeam.AWAY)?away:home).influenceMap;
            map.put(player,100/count);
            accumAtt(team,NodeAtt.DEF,player.getDefending()/count);
            accumAtt(team,NodeAtt.PASS, player.getPassing()/count);
            accumAtt(team,NodeAtt.FIN, player.getPassing()/count);
            accumAtt(team,NodeAtt.POSS, player.getSkillLevel()/count);
        }

        public String toString(){
            return "X: " + position.getX() + "\tY: " + position.getY() + "\t" + attributes.toString();
        }
    }

    public class Grid {
        private GridNode matrix[][];
        private Player homeGK, awayGK;

        public Grid(Formation home, Formation away){

            matrix = new GridNode[pitchSize][pitchSize];
            for(int x=0; x<pitchSize;x++){
                for(int y=0;y<pitchSize;y++){
                    matrix[x][y] = new GridNode(new Point(x,y));
                    SimulationNode sNodeHome = new SimulationNode(matrix[x][y],MyTeam.HOME);
                    SimulationNode sNodeAway = new SimulationNode(matrix[x][y],MyTeam.AWAY);
                    matrix[x][y].setNode(MyTeam.HOME,sNodeHome);
                    matrix[x][y].setNode(MyTeam.AWAY,sNodeAway);
                }
            }

            setFormation(MyTeam.HOME,home);
            setFormation(MyTeam.AWAY,away);

            configureNeighbors(home.getAttitude(),away.getAttitude());
        }

        private SimulationNode kickOff(MyTeam team){
            double rand = Math.random();
            if(team.equals(MyTeam.AWAY)){
                return rand < 0.5?matrix[2][1].away:matrix[2][2].away;
            }
            return rand < 0.5?matrix[1][1].home:matrix[1][2].home;
        }

        private void setFormation(MyTeam team,Formation formation){
            for(Map.Entry entry : formation.getStarters().entrySet()){
                playerInfluence(team,(Point) entry.getValue(), (Player) entry.getKey());
            }
        }

        private void nodeNeighbors(MyTeam team, SimulationNode node, int attitude){
            int x = node.node.position.getX(), y = node.node.position.getY();
            Collection<SimulationNode> neighbors = new HashSet<>();
            int accum = 1;

            for (int xx = 0; xx < pitchSize; xx++) {
                for (int yy = 0; yy < pitchSize; yy++) {
                    int auxX;
                    if(team.equals(MyTeam.HOME)){
                        auxX = xx + 1;
                        if(auxX < pitchSize && auxX > x) {
                            neighbors.add(matrix[auxX][yy].home);
                            accum += node.node.getAtt(team, NodeAtt.PASS)*attitude / Point.manhattanSq(new Point(x, y), new Point(auxX, yy));
                        }
                    }else{
                        auxX = pitchSize-xx-1;
                        if(auxX >= 0 && auxX < x) {
                            neighbors.add(matrix[auxX][yy].away);
                            accum += node.node.getAtt(team, NodeAtt.PASS)*attitude / Point.manhattanSq(new Point(x, y), new Point(auxX, yy));
                        }
                    }
                }
            }

            for(final SimulationNode sNode : neighbors){
                double distance = Point.manhattanSq(sNode.node.position,new Point(x,y));
                int passing = matrix[x][y].getAtt(team,NodeAtt.PASS);
                final double normalizedWeight = passing*100*attitude/(accum*distance);
                node.neighbors.add(new SimulationArc() {
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

        public void configureNeighbors(int homeAttitude, int awayAttitude){
            for (int x = 0; x < pitchSize; x++) {
                for (int y = 0; y < pitchSize; y++) {
                    nodeNeighbors(MyTeam.HOME,matrix[x][y].home, homeAttitude);
                    nodeNeighbors(MyTeam.AWAY,matrix[x][y].away, awayAttitude);
                }
            }
        }

        private void playerInfluence(MyTeam team, Point position, Player player){
            int x = position.getX(), y = position.getY(), count = 0;

            if(team == MyTeam.AWAY) {
                x = pitchSize * 2 - x;
                y = pitchSize * 2 - y;
            }

            if(x == 0 || x == 8 || y == 0 || y == 8)
                return;

            boolean splitX = (x%2 == 0), splitY = (y%2 == 0);
            Collection<GridNode> nodesToInfluence = new HashSet<>();

            x = x/2;
            y = y/2;

            if(splitX) {
                if(splitY){
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
                if(splitY){
                    nodesToInfluence.add(matrix[x][y - 1]);
                    nodesToInfluence.add(matrix[x][y]);
                    count = 2;
                } else {
                    nodesToInfluence.add(matrix[x][y]);
                    count = 1;
                }
            }

            for(GridNode node : nodesToInfluence){
                node.influence(team, player,count);
            }

        }
    }

    private class SimulationNode {
        private final GridNode node;
        private final MyTeam possession;
        private Map<Player,Integer> influenceMap;
        private final Collection<SimulationArc> neighbors;

        private SimulationNode(GridNode node, MyTeam possession) {
            this.node = node;
            this.possession = possession;
            this.influenceMap = new HashMap<>();
            this.neighbors = new HashSet<>();
        }

        private SimulationNode dispute(int minute, List<Event> events){
            int opponentDef = node.getAtt(otherTeam(possession),NodeAtt.DEF);
            int myPoss = node.getAtt(possession,NodeAtt.POSS);

            int sum = myPoss + opponentDef + 1;
            double nMyPoss = myPoss/sum;

            boolean taken =  Math.random()*sum < nMyPoss;

            if(taken) {

                events.add(new Event(0, node.getSNode(otherTeam(possession)).whoDidIt(), Event.Type.TACKLE, minute));
            }

            return node.getSNode(taken?otherTeam(possession):possession);
        }

        private SimulationNode shot(int minute, List<Event> events){
            double random = Math.random();
            double accum = 0;

            int sum =
        }

        private SimulationNode advance(int minute, List<Event> events){
            double random = Math.random();
            double accum = 0;
            SimulationNode last = null;
            for(SimulationArc arc : neighbors){
                accum += arc.getWeight();
                if(random < accum)
                    return arc.getNeighbor();
                last = arc.getNeighbor();
            }
            return last;
        }

        private Player whoDidIt(){
            double action = Math.random();
            int accum = 0;
            Player guilty = null;

            for(Map.Entry entry : influenceMap.entrySet()){
                accum += (int) entry.getValue();
                if(action < accum) {
                    guilty = (Player) entry.getKey();
                }
            }
            return guilty;
        }

        public String toString(){
            return node.toString() + "\t" + possession;
        }

    }

    private interface SimulationArc {
        public double getWeight();
        public SimulationNode getNeighbor();
    }
}
