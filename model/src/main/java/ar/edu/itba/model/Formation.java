package ar.edu.itba.model;

import java.util.List;
import java.util.Map;

public class Formation {
    private Map<Player, Point> formation;
    private List<Player> substitutes;
    private Integer pressure, attitude;
    private Player captain, freeKickTaker, penaltyTaker;

    public Formation(Map<Player, Point> formation, Integer pressure, Integer attitude, Player captain, Player freeKickTaker, Player penaltyTaker) {
        this.formation = formation;
        this.pressure = pressure;
        this.attitude = attitude;
        this.captain = captain;
        this.freeKickTaker = freeKickTaker;
        this.penaltyTaker = penaltyTaker;
    }

    public Map<Player, Point> getFormation() {
        return formation;
    }

    public void setFormation(Map<Player, Point> formation) {
        this.formation = formation;
    }

    public List<Player> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Player> substitutes) {
        this.substitutes = substitutes;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
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
