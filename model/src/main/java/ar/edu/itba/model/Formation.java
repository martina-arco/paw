package ar.edu.itba.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Formation {

    public static final Collection<Point> availablePositions = new HashSet<>();

    public static void init(){
        //availablePositions.add(new Point());
    }

    private final Map<Player, Point> formation;
    private List<Player> substitutes;
    private int pressure, attitude;
    private Player captain, freeKickTaker, penaltyTaker;

    public Formation(final Map<Player, Point> formation, final int pressure, final int attitude, final Player captain, final Player freeKickTaker, final Player penaltyTaker) {
        this.formation = formation;
        this.pressure = pressure;
        this.attitude = attitude;
        this.captain = captain;
        this.freeKickTaker = freeKickTaker;
        this.penaltyTaker = penaltyTaker;
    }

    public void movePlayer(final Player p1, final Point position){
        if(availablePositions.contains(position))
            formation.put(p1,position);
    }

    public void replacePlayer(final Player p1, final Player p2){
        Point position = formation.get(p1);
        formation.remove(p1);
        formation.put(p2,position);
    }

    public Map<Player, Point> getFormation() {
        return formation;
    }

    public List<Player> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Player> substitutes) {
        this.substitutes = substitutes;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public Player getFreeKickTaker() {
        return freeKickTaker;
    }

    public void setFreeKickTaker(Player freeKickTaker) {
        this.freeKickTaker = freeKickTaker;
    }

    public Player getPenaltyTaker() {
        return penaltyTaker;
    }

    public void setPenaltyTaker(Player penaltyTaker) {
        this.penaltyTaker = penaltyTaker;
    }
}
