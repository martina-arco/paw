package ar.edu.itba.model.utils.simulation;

import ar.edu.itba.model.Match;
import ar.edu.itba.model.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class MatchDeepStatus {

    private class State {
        private Point position;
        private int minute;
        private MyTeam team;
    }

    private Match match;
    private List<State> states;

    public MatchDeepStatus(Match match){
        this.match = match;
        states = new ArrayList<>();
    }

    public void registerState(Point pos, int minute, MyTeam team){
        State ret = new State();
        ret.position = new Point(pos.getX(), pos.getY());
        ret.minute = minute;
        ret.team = team;
        states.add(minute, ret);
    }

    public Point positionByMinute(int minute){
        return states.get(minute).position;
    }

    public MyTeam ownerByMinute(int minute){
        return states.get(minute).team;
    }

}
